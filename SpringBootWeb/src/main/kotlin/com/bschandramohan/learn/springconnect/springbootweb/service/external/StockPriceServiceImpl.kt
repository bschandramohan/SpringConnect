package com.bschandramohan.learn.springconnect.springbootweb.service.external

import com.bschandramohan.learn.springconnect.springbootweb.aop.TimeIt
import com.bschandramohan.learn.springconnect.springbootweb.domain.StockPrice
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.net.URL

@Service
class StockPriceServiceImpl : StockPriceService {
    @Value("\${stockservice.apikey}")
    lateinit var accessKey: String

    var logger: Logger = LoggerFactory.getLogger(StockPriceServiceImpl::class.java)

    @TimeIt
    override fun getStockPrice(symbol: String): StockPrice {
        try {
            val stockPriceData = URL("http://api.marketstack.com/v1/intraday/?access_key=$accessKey&symbols=$symbol")
                    .readText()
            logger.info(stockPriceData)
        } catch (e: Exception) {
            logger.error("Caught exception reading the stockprice for symbol=$symbol ex=${e.message}")
            // Ignore for now
        }
        return StockPrice(symbol, 100.0)
    }
}