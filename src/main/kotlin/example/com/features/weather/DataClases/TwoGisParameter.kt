package example.com.features.weather.DataClases

import kotlinx.serialization.Serializable

@Serializable
data class TwoGisParameter(
    val meta: Meta,
    val result: Result
)