package tech.zingu.kontent

import kotlinx.html.HTML
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import tech.zingu.kontent.templates.DefaultTemplate
import tech.zingu.kontent.templates.Template
import tech.zingu.kontent.templates.html
import tech.zingu.kontent.templates.html5up.HTML5UPIdentityTemplate

@Serializable
data class Config<T>(
    @SerialName("template")
    val template: String,
    @SerialName("title")
    val title: String,
    @SerialName("favicon")
    val favicon: String,
    @SerialName("resources")
    val resources: String,
    @SerialName("data")
    val data: T
)

private fun getTemplate(name: String): Template<out Any> = when (name) {
    "default" -> DefaultTemplate()
    "html5up-identity" -> HTML5UPIdentityTemplate()
    else -> throw UnknownTemplateException("Can't find template with name $name")
}

private fun String.fullTrim(): String =
    this.replace(Regex("\\s+"), "")

/**
 * struct sample:
 *
 * template: default
 * title: My Blog
 * favicon: some.ico
 * resources: ./css
 *
 * data: {
 *   ...
 * }
 */
@OptIn(ExperimentalSerializationApi::class)
fun <T> parseConfig(config: String): Config<T> =
    ConfigSerializer<T>().deserialize(StringDecoder(config))

internal fun <T> parseConfig(config: String, dataBuilder: (String) -> T): Config<T> =
    ConfigSerializer(templateFinder = {
        object : Template<T> {
            override fun templateSample(): String = ""
            override fun parseData(configData: String): T = dataBuilder(configData)
            override fun layout(config: Config<T>): HTML = html {}
        }
    }).deserialize(StringDecoder(config))

@OptIn(ExperimentalSerializationApi::class)
private class StringDecoder(private val raw: String) : AbstractDecoder() {
    override val serializersModule: SerializersModule = EmptySerializersModule()

    private var used = false
    private var index = -1

    override fun decodeString(): String {
        check(!used) { "single-use decoder" }
        used = true
        return raw
    }

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        index++
        return if (index == 0) 0 else CompositeDecoder.DECODE_DONE
    }
}

private class ConfigSerializer<T>(
    private val templateFinder: (String) -> Template<T> = { name ->
        @Suppress("UNCHECKED_CAST")
        getTemplate(name) as Template<T>
    }
) : KSerializer<Config<T>> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("ConfigSerializer", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Config<T> {
        try {
            val config = decoder.decodeString()
            val baseConfig = parseBaseConfig(config)

            @Suppress("UNCHECKED_CAST")
            val template = templateFinder(baseConfig.template)

            return Config(
                template = baseConfig.template,
                title = baseConfig.title,
                favicon = baseConfig.favicon,
                resources = baseConfig.resources,
                data = template.parseData(baseConfig.dataContent)
            )
        } catch (error: InvalidParamsException) {
            throw error
        } catch (error: Throwable) {
            throw InvalidParamsException(error.message ?: "can't parse config: malformed kontent!")
        }
    }

    override fun serialize(encoder: Encoder, value: Config<T>) {
        /* no-op: this is just a deserializer. */
    }

    private data class BaseConfig(
        val template: String,
        val title: String,
        val favicon: String,
        val resources: String,
        val dataContent: String
    ) {
        companion object {
            val ROOT_LEVEL_REGEX = """(\w+(?:-\w+)?)\s*:\s*([^{\n]+)""".toRegex()
            val OBJECT_START_REGEX = """(\w+(?:-\w+)?)\s*:\s*\{""".toRegex()
            val OBJECT_END_REGEX = """\}""".toRegex()
            //val KEY_VALUE_REGEX = """(\w+(?:-\w+)?)\s*:\s*([^,\n\}]+)""".toRegex()
            //val ARRAY_REGEX = """\[(.*?)\]""".toRegex()
        }
    }

    private fun parseBaseConfig(config: String) : BaseConfig {
        val lines = config.lines()
        val rootValues = mutableMapOf<String, String>()
        var inData = false
        val dataContent = StringBuilder()
        var blockCount = 0

        for (line in lines) {
            val trimmedLine = line.trim()
            if (trimmedLine.isEmpty()) continue

            when {
                // match root level key-value pairs
                !inData && BaseConfig.ROOT_LEVEL_REGEX.matches(trimmedLine) -> {
                    val match = BaseConfig.ROOT_LEVEL_REGEX.find(trimmedLine)!!
                    val (key, value) = match.destructured
                    rootValues[key] = value.trim()
                }
                // start of `data` section
                trimmedLine.fullTrim().startsWith("data:{") -> {
                    inData = true
                    blockCount++
                }
                // start of a nested object
                inData && BaseConfig.OBJECT_START_REGEX.matches(trimmedLine) -> {
                    dataContent.appendLine(trimmedLine)
                    blockCount++
                }
                // end of a nested object
                inData && BaseConfig.OBJECT_END_REGEX.matches(trimmedLine) -> {
                    blockCount--

                    if (blockCount > 0) {
                        dataContent.appendLine(trimmedLine)
                    } else {
                        inData = false
                    }
                }
                // key-value pairs inside objects or params
                inData -> {
                    dataContent.appendLine(trimmedLine)
                    trimmedLine.forEach { c ->
                        if (c == '{') blockCount++
                        if (c == '}') {
                            blockCount--
                            if (blockCount == 0) {
                                inData = false
                            }
                        }
                    }
                }
            }
        }

        // convert the collected maps to basic config data
        return BaseConfig(
            template = rootValues["template"] ?: "",
            title = rootValues["title"] ?: "",
            favicon = rootValues["favicon"] ?: "",
            resources = rootValues["resources"] ?: "",
            dataContent = dataContent.toString()
        )
    }
}

internal fun <T> String.parseKeyAsList(name: String, itemBuilder: (String) -> T): List<T> {
    val section = getSection(name, '[', ']')
    if (section.isEmpty()) return emptyList()

    var items = section.split("\n")

    if (items.isEmpty() || (items.size == 1 && items[0].contains(","))) {
        items = section.split(',')
    }

    return items.map { it.trim().removeSuffix(",") }
        .filter { it.isNotEmpty() }
        .map(itemBuilder)
}

internal fun <T> String.parseKeyAsList(name: String, itemBuilder: (String, String) -> T): List<T> {
    val section = getSection(name, '{', '}')
    if (section.isEmpty()) return emptyList()

    val items = mutableListOf<T>()
    var inKey = true
    var blocksCount = 0
    var key = ""
    var value = ""

    val hasMultipleLines = section.indexOf('\n') >= 0 || section.indexOf('\r') >= 0
    val separator = if (hasMultipleLines) '\n' else ','

    section.forEach { char ->
        if (char == ':' && blocksCount == 0) inKey = false
        else if (char != separator) {
            if (inKey) key += char else value += char
        }
        if (char == '{' || char == '[') blocksCount++
        if (char == '}' || char == ']') blocksCount--
        if (char == separator && !inKey && blocksCount == 0) {
            inKey = true
            items.add(itemBuilder(key.trim(), value.trim()))
            key = ""
            value = ""
        }
    }

    if (key.isNotBlank()) {
        items.add(itemBuilder(key.trim(), value.trim()))
    }

    return items
}

internal fun <T> String.parseKeyAsSet(name: String, itemBuilder: (String) -> T): Set<T> =
    parseKeyAsList(name, itemBuilder).toSet()

internal fun <K, V> String.parseKeyAsMap(name: String, itemBuilder: (String, String) -> Pair<K, V>): Map<K, V> {
    val section = getSection(name, '{', '}')
    if (section.isEmpty()) return emptyMap()

    val items = mutableMapOf<K,V>()
    var inKey = true
    var blocksCount = 0
    var key = ""
    var value = ""

    section.forEach { char ->
        if (char == ':' && blocksCount == 0) inKey = false
        else if (inKey) key += char else value += char
        if (char == '{' || char == '[') blocksCount++
        if (char == '}' || char == ']') blocksCount--
        if (char == '\n' && !inKey && blocksCount == 0) {
            inKey = true
            itemBuilder(key.trim(), value.trim())
                .also { items[it.first] = it.second }
            key = ""
            value = ""
        }
    }

    return items
}

internal fun String.parseMultiLineString(name: String): String {
    // find the line where the key appears: ^\s*name\s*:
    val keyPattern = Regex("^\\s*${Regex.escape(name)}\\s*:", RegexOption.MULTILINE)
    val keyMatch = keyPattern.find(this) ?: throw InvalidParamsException("can't find key $name")

    // start searching for the beginning of the value on the matched line
    val searchStart = keyMatch.range.first
    val openIdx = indexOf("|\"", startIndex = searchStart)
    if (openIdx == -1) throw InvalidParamsException("no multiple line string set for key $name")

    var line = ""
    var result = ""
    var index = openIdx + 2

    while (line != "\"|" && index < length) {
        line = substring(index, indexOf("\n", index + 1))
        val lineFullTrim = line.fullTrim()
        if (lineFullTrim != "\"|") result += line else line = lineFullTrim
        index += line.length
    }

    if (line != "\"|") throw InvalidParamsException("malformed multiple line string for key $name")

    return result.trim()
}

internal fun <T> String.parseKeyAsItem(name: String, transformer: (String) -> T): T {
    val content = trim().removePrefix("{").removeSuffix("}")
    val keyPattern = Regex("\\s*${Regex.escape(name)}\\s*:")
    val keyMatch = keyPattern.find(content) ?: throw InvalidParamsException("can't find key $name")

    // start searching for the beginning of the value on the matched line
    val searchStart = keyMatch.range.first
    var openIdx = content.indexOf(name, startIndex = searchStart)
    if (openIdx == -1) throw InvalidParamsException("no value set for key $name")
    openIdx += name.length

    // find the very first end line char after the first beginning of the value and ignore anything beyond it
    val hasMultipleLines = content.indexOf('\n') >= 0 || content.indexOf('\r') >= 0
    val separator = if (hasMultipleLines) '\n' else ','
    var closeIdx = content.indexOf(separator, startIndex = openIdx + 1)
    if (closeIdx == -1) closeIdx = content.length // throw InvalidParamsException("malformed item for key $name")

    // convert the found value content with the transformer
    return transformer(content.substring(openIdx + 1, closeIdx).trim())
}

private fun String.getSection(name: String, prefix: Char, suffix: Char): String {
    // find the line where the key appears: ^\s*name\s*:
    val keyPattern = Regex("^\\s*${Regex.escape(name)}\\s*:", RegexOption.MULTILINE)
    val keyMatch = keyPattern.find(this) ?: throw InvalidParamsException("can't find key $name")

    // start searching for '$prefix' from the beginning of the matched line
    val searchStart = keyMatch.range.first
    val openIdx = indexOf(prefix, startIndex = searchStart)
    if (openIdx == -1) throw InvalidParamsException("no value set for key $name")

    var result = ""
    var blocksCount = 1
    var charIdx = openIdx

    // find the very last closing '$suffix' after the first '$prefix' and ignore anything beyond it
    while (blocksCount > 0) {
        val char = this[++charIdx]
        if (char == prefix) blocksCount++
        if (char == suffix) blocksCount--
        if (blocksCount > 0) result += char
    }

    return result
}
