package com.bschandramohan.learn.springconnect.springbootweb.api

import com.bschandramohan.learn.springconnect.springbootweb.aop.TimeIt
import com.bschandramohan.learn.springconnect.springbootweb.domain.MorningInfo
import com.bschandramohan.learn.springconnect.springbootweb.domain.Preferences
import com.bschandramohan.learn.springconnect.springbootweb.service.MorningInfoService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Min

@RestController
class MorningInfoApi(var morningInfoService: MorningInfoService) {
    @TimeIt
    @GetMapping(value = ["/MorningInfo"])
    fun getMorningInfo() = morningInfoService.getMorningInfo()

    @TimeIt
    @GetMapping(value = ["/MorningInfo/{zipCode}"])
    fun getMorningInfoForZip(@Min(1000) @PathVariable zipCode: Int): MorningInfo {
        logger.info("ZipCode=$zipCode")
        // TEMP: For now just return default
        return morningInfoService.getMorningInfo()
    }

    @TimeIt
    @GetMapping(value = ["/MorningInfoForPrefs"])
    fun getMorningInfoForPreferences(@Valid @RequestBody preferences: Preferences): MorningInfo {
        logger.info("${preferences.weatherZipCode} passed")
        // TEMP: For now just return default
        return morningInfoService.getMorningInfo()
    }

    private val logger = LoggerFactory.getLogger(MorningInfoApi::class.java)
}