package com.bschandramohan.learn.springconnect.springbootweb.service

import com.bschandramohan.learn.springconnect.springbootweb.aop.TimeIt
import com.bschandramohan.learn.springconnect.springbootweb.domain.MorningInfo
import com.bschandramohan.learn.springconnect.springbootweb.service.external.StockPriceService
import com.bschandramohan.learn.springconnect.springbootweb.service.external.WeatherService
import org.springframework.stereotype.Service

@Service
class MorningInfoServiceImpl(var weatherService: WeatherService, var stockPriceService: StockPriceService)
    : MorningInfoService {
    @TimeIt
    override fun getMorningInfo(weatherZipCode: String): MorningInfo {
        val stockPrice = stockPriceService.getStockPrice()
        val weatherInfo = weatherService.getWeather(weatherZipCode)
        return MorningInfo("", listOf(stockPrice))
    }
}