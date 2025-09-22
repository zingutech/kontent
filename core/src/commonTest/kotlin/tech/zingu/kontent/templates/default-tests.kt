package tech.zingu.kontent.templates

import tech.zingu.kontent.Config
import tech.zingu.kontent.parseConfig
import kotlin.test.Test
import kotlin.test.assertEquals

class DefaultTemplateTests {

    @Test
    fun `test parser for the default template config`() {
        val config = """
            template: default
            title: My Blog
            favicon: some.ico
            resources: ./css
            
            data: {
              header-img: photo.png
              title: Some Title
              tags: [tag1, tag2, tag3]
              social-networks: {
                github: https://github.com/myname
                medium: https://medium.com/@myname
                linkedin: https://www.linkedin.com/in/myname
              }
              footer-text: © My Name. All rights reserved.
            }
        """.trimIndent()

        val result: Config<DefaultTemplate.Params> = parseConfig(config)

        assertEquals("default", result.template)
        assertEquals("My Blog", result.title)
        assertEquals("some.ico", result.favicon)
        assertEquals("./css", result.resources)
        assertEquals("photo.png", result.data.headerImg)
        assertEquals("Some Title", result.data.title)

        assertEquals("© My Name. All rights reserved.", result.data.footerText)
    }
}
