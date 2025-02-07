package example.com.features.weather

import io.ktor.server.application.*
import io.ktor.server.routing.*


fun Application.configureWeatherRouting(){
    routing {
        get("/weather"){
            val controller = WeatherController(call)
            controller.getWeather()
        }
    }
}