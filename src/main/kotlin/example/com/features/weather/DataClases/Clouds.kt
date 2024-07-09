package example.com.features.weather.DataClases

import kotlinx.serialization.Serializable

@Serializable
data class Clouds(
    val all: Int
)