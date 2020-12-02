package com.bschandramohan.learn.springconnect.springbootweb.service.external

import com.bschandramohan.learn.springconnect.springbootweb.domain.StockPrice

interface StockPriceService {
    fun getStockPrice(symbol: String = "INTU"): StockPrice
}