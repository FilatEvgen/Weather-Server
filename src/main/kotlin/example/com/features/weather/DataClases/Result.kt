package example.com.features.weather.DataClases

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val items: List<Item>,
    val total: Int
)