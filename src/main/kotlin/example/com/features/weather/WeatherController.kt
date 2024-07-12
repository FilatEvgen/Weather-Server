package example.com.features.weather

import example.com.features.weather.DataClases.FinalModel
import example.com.features.weather.DataClases.TwoGisParameter
import example.com.features.weather.DataClases.WeatherRemote
import example.com.utils.ApiRoutes
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.serialization.json.Json
import ru.novbicreate.features.translater.TranslateService

class WeatherController(private val call: ApplicationCall) {
    private val key = System.getenv("KEYWEATHER")
    private val key2Gis = System.getenv("KEY2GIS")

    private val client = HttpClient(CIO) {
        install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getWeather() {
        try {
            val language = call.request.queryParameters["language"]
            val cityParams = call.request.queryParameters["city"]
            val apiCoord: HttpResponse = client.get(ApiRoutes.GET_COORDINATES){
                parameter("q", cityParams)
                parameter("key", key2Gis)
                parameter("fields", "items.point")
            }
            val cityData: TwoGisParameter = apiCoord.body()
            val lat = cityData.result.items.first().point.lat.toString()
            val lon = cityData.result.items.first().point.lon.toString()
            val apiWeather: HttpResponse = client.get(ApiRoutes.GET_WEATHER) {
                parameter("lat", lat)
                parameter("lon", lon)
                parameter("appid", key)
            }
            val weatherResponse: WeatherRemote = apiWeather.body()
            val city = weatherResponse.name
            val description = weatherResponse.weather.map { it.description }
            val temp = weatherResponse.main.temp.toInt()
            val translateTest = "$city; ${description.joinToString("; ")}"
            val translateWeather = TranslateService(language, client).translateText(translateTest)
            translateWeather?.let { pars ->
                val parsList = pars.split("; ")
                val finalResult = FinalModel(parsList.first(), parsList.last(), fahrenheitToCelsius(temp), weatherResponse.main.humidity, weatherResponse.wind.speed)
                call.respond(finalResult)
            } ?: run {
                call.respond(HttpStatusCode.BadGateway, "Ошибка перевода")
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadGateway, "Ошибка: ${e.message}")
        }
    }
    private fun fahrenheitToCelsius(value: Int): Int{
        return (value - 273.15).toInt()
    }
}
