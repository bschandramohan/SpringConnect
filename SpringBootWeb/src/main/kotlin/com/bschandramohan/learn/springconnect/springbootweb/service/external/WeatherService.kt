package com.bschandramohan.learn.springconnect.springbootweb.service.external

interface WeatherService {
    fun getWeather(zipCode: String = "95014"): String
}