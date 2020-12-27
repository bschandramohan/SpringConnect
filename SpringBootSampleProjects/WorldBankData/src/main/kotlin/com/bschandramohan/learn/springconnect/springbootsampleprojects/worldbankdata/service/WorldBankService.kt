package com.bschandramohan.learn.springconnect.springbootsampleprojects.worldbankdata.service

interface WorldBankService {
    fun getGdp(countryCode: String): String
}