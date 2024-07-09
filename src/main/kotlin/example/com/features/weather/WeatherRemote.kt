package example.com.features.weather

import example.com.features.weather.DataClases.*
import kotlinx.serialization.Serializable

@Serializable
data class WeatherRemote(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val rain: Rain?=null,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val wind: Wind
)
