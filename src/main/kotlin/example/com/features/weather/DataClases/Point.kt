package example.com.features.weather.DataClases

import kotlinx.serialization.Serializable

@Serializable
data class Point(
    val lat: Double,
    val lon: Double
)