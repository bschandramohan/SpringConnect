package com.bschandramohan.learn.springconnect.springbootweb.service.external

import com.bschandramohan.learn.springconnect.springbootweb.aop.TimeIt
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.net.URL

@Service
class WeatherServiceImpl : WeatherService {

    @Value("\${weatherservice.apikey}")
    lateinit var weatherApiKey: String

    var logger: Logger = LoggerFactory.getLogger(WeatherServiceImpl::class.java)

    @Cacheable("Weather")
    @TimeIt
    override fun getWeather(zipCode: String): String {
        return try {
            val weatherData = URL("https://api.openweathermap.org/data/2.5/weather?q=$zipCode&appId=$weatherApiKey")
                    .readText()
            logger.info(weatherData)
            weatherData
        } catch (e: Exception) {
            logger.error("Exception caught while fetching the values of the weather for zipCode=$zipCode; ex=${e.message}")
            ""
        }
    }
}