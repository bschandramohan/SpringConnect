package com.bschandramohan.learn.springconnect.springbootweb.domain

data class StockPrice(var symbol: String, var currentPrice: Double)
data class MorningInfo(var weatherReport: String, var stockPrices: List<StockPrice> = listOf())
