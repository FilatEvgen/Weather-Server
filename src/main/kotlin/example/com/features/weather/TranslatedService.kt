package ru.novbicreate.features.translater

import example.com.features.weather.DataClases.TranslateJoke
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*


class TranslateService(private val language: String?, private val client: HttpClient) {
    suspend fun translateText(text: String): String? {
        try {
            val translateResponse: HttpResponse = client.get("https://api.mymemory.translated.net/get") {
                header("User-Agent", "Mozilla/5.0")
                header("Accept-Charser", "UTF-8")
                parameter("q", text)
                parameter("langpair", "eu|${getLanguageCode(language)}")
            }
            val translateJoke: TranslateJoke = translateResponse.body()
            return translateJoke.responseData.translatedText
        }catch (e: Exception) {
            println(e.localizedMessage)
            return null
        }
    }
    private fun getLanguageCode(language: String?): String {
        return when (language) {
            "russian" -> "ru"
            "english" -> "eu"
            "spanish" -> "es"
            else -> "eu"
        }
    }
}