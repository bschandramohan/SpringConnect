package com.bschandramohan.learn.springconnect.springbootweb.domain

import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class StockPrice(var symbol: String, var currentPrice: Double)
data class MorningInfo(var weatherReport: String, var stockPrices: List<StockPrice> = listOf())

data class Preferences(@Max(100000) @Min(10) var weatherZipCode: Int, var stockSymbols: List<String> = listOf())