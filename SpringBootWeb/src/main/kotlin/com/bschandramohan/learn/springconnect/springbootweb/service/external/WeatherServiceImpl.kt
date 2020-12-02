package com.bschandramohan.learn.springconnect.springbootweb.service.external

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.net.URL

@Service
class WeatherServiceImpl : WeatherService {

    @Value("\${weatherservice.apikey}")
    lateinit var weatherApiKey: String

    override fun getWeather(zipCode: String): String {
        return try {
            val weatherData = URL("https://api.openweathermap.org/data/2.5/weather?q=$zipCode&appId=$weatherApiKey")
                    .readText()
            weatherData
        } catch (e: Exception) {
            ""
        }
    }
}