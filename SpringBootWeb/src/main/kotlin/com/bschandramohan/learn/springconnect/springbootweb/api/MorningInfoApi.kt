package com.bschandramohan.learn.springconnect.springbootweb.api

import com.bschandramohan.learn.springconnect.springbootweb.aop.TimeIt
import com.bschandramohan.learn.springconnect.springbootweb.service.MorningInfoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MorningInfoApi(var morningInfoService: MorningInfoService) {
    @TimeIt
    @GetMapping(value = ["/MorningInfo"])
    fun getMorningInfo() = morningInfoService.getMorningInfo()
}