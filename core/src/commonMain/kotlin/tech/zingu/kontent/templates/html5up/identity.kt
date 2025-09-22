package tech.zingu.kontent.templates.html5up

import kotlinx.html.HTML
import kotlinx.html.a
import kotlinx.html.article
import kotlinx.html.aside
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.footer
import kotlinx.html.h1
import kotlinx.html.h2
import kotlinx.html.head
import kotlinx.html.header
import kotlinx.html.i
import kotlinx.html.link
import kotlinx.html.main
import kotlinx.html.meta
import kotlinx.html.p
import kotlinx.html.style
import kotlinx.html.title
import kotlinx.html.unsafe
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tech.zingu.kontent.Config
import tech.zingu.kontent.templates.Template
import tech.zingu.kontent.templates.html

class HTML5UPIdentityTemplate : Template<HTML5UPIdentityTemplate.Params> {
    @Serializable
    data class Params(
        @SerialName("title")
        val title: String,
        @SerialName("subtitle")
        val subTitle: String,
        @SerialName("social-networks")
        val socialNetworks: List<SocialNetwork>,
        @SerialName("projects")
        val projects: List<Project>,
        @SerialName("footer-text")
        val footerText: String
    ) {
        @Serializable
        data class Project(
            @SerialName("name")
            val name: String,
            @SerialName("url")
            val url: String
        )

        @Serializable
        data class SocialNetwork(
            // Sample: github, medium, linkedin, twitter, instagram, youtube
            @SerialName("name")
            val name: String,
            @SerialName("url")
            val url: String
        )
    }

    override fun templateSample(): String = """
        template: html5up-identity
        title: My Blog
        favicon: some.ico
        resources: ./css
        
        data: {
          background-image: background.png
          header-img: photo.png
          title: Some Title
          subtitle: Some info
          social-networks: {
            github: https://github.com/myname
            medium: https://medium.com/@myname
            linkedin: https://www.linkedin.com/in/myname
          }
          projects: {
            Konnection: https://github.com/TM-Apps/konnection
            State Tools: https://github.com/TM-Apps/state_tools
            Kontent: https://github.com/TM-Apps/kontent
          }
          footer-text: My Name
        }
    """.trimIndent()

    override fun parseData(configData: String): Params {
        TODO("Not yet implemented")
    }

    override fun layout(config: Config<Params>): HTML = html {
        head {
            meta(charset = "UTF-8")
            meta(name = "viewport", content = "width=device-width, initial-scale=1.0")
            title("My Blog")

            // font awesome CSS
            link(rel = "stylesheet", href = "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css")

            // styles
            style {
                unsafe {
                    raw(css)
                }
            }
        }
        body {
            div("container") {
                aside("left-column") {
                    header {
                        h1 { +"My Blog" }
                    }
                    footer("footer") {
                        div("footer-content") {
                            div("social-icons") {
                                // GitHub
                                a(href = "https://github.com/yourusername", target = "_blank") {
                                    attributes["aria-label"] = "GitHub"
                                    i("fab fa-github") {}
                                }
                                // LinkedIn
                                a(href = "https://linkedin.com/in/yourusername", target = "_blank") {
                                    attributes["aria-label"] = "LinkedIn"
                                    i("fab fa-linkedin") {}
                                }
                                // Twitter
                                a(href = "https://twitter.com/yourusername", target = "_blank") {
                                    attributes["aria-label"] = "Twitter"
                                    i("fab fa-twitter") {}
                                }
                                // Instagram
                                a(href = "https://instagram.com/yourusername", target = "_blank") {
                                    attributes["aria-label"] = "Instagram"
                                    i("fab fa-instagram") {}
                                }
                                // YouTube
                                a(href = "https://youtube.com/@yourusername", target = "_blank") {
                                    attributes["aria-label"] = "YouTube"
                                    i("fab fa-youtube") {}
                                }
                            }
                            p {
                                +"© 2025 My Blog. All rights reserved."
                            }
                        }
                    }
                }

                main("posts") {
                    // First post
                    article("post") {
                        h2 { +"Sample Blog Post Title" }
                        div("post-meta") {
                            +"Posted on July 28, 2025 by Author"
                        }
                        div("post-excerpt") {
                            p {
                                +"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris."
                            }
                        }
                        a(href = "post1.html", classes = "read-more-btn") {
                            +"Read More"
                        }
                    }

                    // Second post
                    article("post") {
                        h2 { +"Another Blog Post Title" }
                        div("post-meta") {
                            +"Posted on July 27, 2025 by Author"
                        }
                        div("post-excerpt") {
                            p {
                                +"Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident."
                            }
                        }
                        a(href = "post2.html", classes = "read-more-btn") {
                            +"Read More"
                        }
                    }
                }
            }
        }
    }

    private val css = """
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            min-height: 100vh;
        }

        .container {
            display: grid;
            grid-template-columns: 300px 1fr;
            min-height: 100vh;
            max-width: 1200px;
            margin: 0 auto;
        }

        .left-column {
            background-color: #f5f5f5;
            padding: 20px;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        header {
            text-align: center;
            padding: 20px 0;
        }

        header h1 {
            color: #333;
        }

        .footer {
            padding: 20px 0;
            text-align: center;
            color: #666;
        }

        .footer-content {
            display: flex;
            flex-direction: column;
            gap: 20px;
            align-items: center;
        }

        .social-icons {
            display: flex;
            gap: 15px;
            justify-content: center;
            flex-wrap: wrap;
        }

        .social-icons a {
            color: #333;
            text-decoration: none;
            font-size: 20px;
            width: 40px;
            height: 40px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            transition: all 0.3s ease;
            background-color: white;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }

        .social-icons a:hover {
            transform: translateY(-3px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.2);
        }

        .social-icons .fa-github:hover {
            color: #333;
            background-color: #f0f0f0;
        }

        .social-icons .fa-linkedin:hover {
            color: #0077b5;
            background-color: #e8f4f9;
        }

        .social-icons .fa-twitter:hover {
            color: #1DA1F2;
            background-color: #e8f5fd;
        }

        .social-icons .fa-instagram:hover {
            color: #E4405F;
            background-color: #fde8ec;
        }

        .social-icons .fa-youtube:hover {
            color: #FF0000;
            background-color: #fde8e8;
        }

        .posts {
            padding: 20px;
        }

        .post {
            margin-bottom: 30px;
            padding: 20px;
            border-bottom: 1px solid #eee;
        }

        .post h2 {
            color: #333;
            margin-bottom: 10px;
        }

        .post-meta {
            color: #666;
            font-size: 0.9em;
            margin-bottom: 15px;
        }

        .post-excerpt {
            margin-bottom: 20px;
        }

        .read-more-btn {
            display: inline-block;
            padding: 8px 16px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        .read-more-btn:hover {
            background-color: #0056b3;
        }

        @media (max-width: 768px) {
            .container {
                grid-template-columns: 1fr;
            }

            .left-column {
                order: 1;
            }

            .posts {
                order: 2;
            }
        }
    """.trimIndent()
}
