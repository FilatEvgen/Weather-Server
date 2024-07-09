package example.com.features.weather.DataClases

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val point: Point,
)