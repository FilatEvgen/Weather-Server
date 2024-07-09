package example.com.features.weather.DataClases

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("rain")
data class Rain(val `1h`: Double)