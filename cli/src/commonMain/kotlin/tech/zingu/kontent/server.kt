package tech.zingu.kontent

import io.ktor.http.ContentType
import io.ktor.server.cio.CIO
import io.ktor.server.engine.EmbeddedServer
import io.ktor.server.engine.connector
import io.ktor.server.engine.embeddedServer
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.coroutines.runBlocking

class HttpServer(
    private val port: Int = 8080
) {
    private var server: EmbeddedServer<*, *>? = null

    fun start() {
        server = embeddedServer(
            factory = CIO,
            configure = {
                connector {
                    port = this@HttpServer.port
                }
            }
        ) {
            routing {
                get("/") {
                    call.respondText("Kontent server is running!", ContentType.Text.Plain)
                }

                get("/health") {
                    call.respond(mapOf("status" to "healthy"))
                }

                // Example endpoint with a path parameter
                get("/hello/{name}") {
                    val name = call.parameters["name"] ?: "World"
                    call.respondText("Hello, $name!")
                }
            }
        }.start(wait = false)

        println("Server started on port $port")
    }

    fun stop() {
        runBlocking {
            server?.stop(gracePeriodMillis = 1000, timeoutMillis = 2000)
        }
        println("Server stopped")
    }
}
