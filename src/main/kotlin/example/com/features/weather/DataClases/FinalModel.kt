package example.com.features.weather.DataClases

import kotlinx.serialization.Serializable

@Serializable
data class FinalModel(
    val city: String,
    val description: String,
    val temperature: Int,
    val humidity: Int,
    val windSpeed: Double
)
@Serializable
data class TranslateJoke(
    val responseData: ResponseData
)

@Serializable
data class ResponseData(
    val match: Double,
    val translatedText: String
)
