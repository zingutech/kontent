package tech.zingu.kontent

import kotlin.test.Test
import kotlin.test.assertEquals

class ConfigTests {

    @Test
    fun `test parseConfig for a basic template`() {
        data class BasicData(val key: String)

        val config = """
            template: basic
            title: Some Title
            favicon: some.ico
            resources: ./css

            data: {
              key: value
            }
        """.trimIndent()

        val result: Config<BasicData> = parseConfig(config) {
            val (key, value) = it.split(":")
            BasicData(key = value.trim())
        }

        assertEquals("basic", result.template)
        assertEquals("Some Title", result.title)
        assertEquals("some.ico", result.favicon)
        assertEquals("./css", result.resources)
        assertEquals("value", result.data.key)
    }

    @Test
    fun `test parseConfig for a template with collections`() {
        data class ListData(
            val list: List<String>,
            val set: Set<String>,
            val map: Map<String, Int>
        )

        val config = """
            template: basic
            title: Some Title
            favicon: some.ico
            resources: ./css

            data: {
              list: [ one
                two
                two
              ]
              set: [ one, two, two ]
              map: {
                one: 1
                two: 2
              }
            }
        """.trimIndent()

        val result: Config<ListData> = parseConfig(config) { item ->
            ListData(
                list = item.parseKeyAsList("list") { it },
                set = item.parseKeyAsSet("set") { it },
                map = item.parseKeyAsMap("map") { key, value -> key to value.toInt() }
            )
        }

        assertEquals("basic", result.template)
        assertEquals("Some Title", result.title)
        assertEquals("some.ico", result.favicon)
        assertEquals("./css", result.resources)
        assertEquals(listOf("one", "two", "two"), result.data.list)
        assertEquals(setOf("one", "two"), result.data.set)
        assertEquals(mapOf("one" to 1, "two" to 2), result.data.map)
    }

    @Test
    fun `test parseConfig for a template with complex collections`() {
        data class Item(val strValue: String, val intValue: Int)

        data class ListData(
            val list: List<Item>,
            val list2: Map<String, Item>
        )

        val config = """
            template: basic
            title: Some Title
            favicon: some.ico
            resources: ./css

            data: {
              listFromMap: { one: 1, two: 2 }
              mapOfData: {
                one: { strValue: number one, intValue: 1 }
                two: {
                    strValue: number two
                    intValue: 2
                }
              }
            }
        """.trimIndent()

        val result: Config<ListData> = parseConfig(config) { item ->
            ListData(
                list = item.parseKeyAsList("listFromMap") { key, value ->
                    Item(strValue = key, intValue = value.toInt())
                },
                list2 = item.parseKeyAsMap("mapOfData") { key, value ->
                    key to Item(
                        strValue = value.parseKeyAsItem("strValue") { it },
                        intValue = value.parseKeyAsItem("intValue") { it.toInt() }
                    )
                }
            )
        }

        assertEquals("basic", result.template)
        assertEquals("Some Title", result.title)
        assertEquals("some.ico", result.favicon)
        assertEquals("./css", result.resources)
        assertEquals(
            listOf(
                Item(strValue = "one", intValue = 1),
                Item(strValue = "two", intValue = 2)),
            result.data.list)
        assertEquals(
            mapOf(
                "one" to Item(strValue = "number one", intValue = 1),
                "two" to Item(strValue = "number two", intValue = 2)),
            result.data.list2)
    }

    @Test
    fun `test parseConfig for a template with a multiple line string`() {
        data class BasicData(val key: String)

        val config = """
            template: basic
            title: Some Title
            favicon: some.ico
            resources: ./css

            data: {
              key: |"
                multiline
                string
              "|
            }
        """.trimIndent()

        val result: Config<BasicData> = parseConfig(config) {
            BasicData(key = it.parseMultiLineString("key"))
        }

        assertEquals("basic", result.template)
        assertEquals("Some Title", result.title)
        assertEquals("some.ico", result.favicon)
        assertEquals("./css", result.resources)
        assertEquals("""
            multiline
            string
        """.trimIndent(), result.data.key)
    }
}
