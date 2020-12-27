package com.bschandramohan.learn.springconnect.springbootsampleprojects.worldbankdata.domain

// Generated using https://www.json2kotlin.com/ from the response from:
// http://api.worldbank.org/v2/countries/IN/indicators/NY.GDP.MKTP.CD?format=json&date=2019

data class WorldBankData (
    val indicator : Indicator,
    val country : Country,
    val countryiso3code : String,
    val date : Int,
    val value : Long, // Using long instead of double for display purposes
    val unit : String,
    val obs_status : String,
    val decimal : Int
)

data class Country (
    val id : String,
    val value : String
)

data class Indicator (
    val id : String,
    val value : String
)
