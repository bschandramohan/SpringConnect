package com.bschandramohan.learn.springconnect.springbootweb.service

import com.bschandramohan.learn.springconnect.springbootweb.domain.MorningInfo

interface MorningInfoService {
    fun getMorningInfo(): MorningInfo
}