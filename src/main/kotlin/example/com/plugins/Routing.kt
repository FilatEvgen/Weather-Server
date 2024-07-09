package example.com.plugins

import example.com.features.weather.configureWeatherRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    configureWeatherRouting()
}
