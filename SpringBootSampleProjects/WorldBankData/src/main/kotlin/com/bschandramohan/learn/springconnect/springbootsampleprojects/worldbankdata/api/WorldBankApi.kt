package com.bschandramohan.learn.springconnect.springbootsampleprojects.worldbankdata.api

import com.bschandramohan.learn.springconnect.springbootsampleprojects.worldbankdata.service.WorldBankService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["worldbank"])
class WorldBankApi(val worldBankService: WorldBankService) {

    @GetMapping(value = ["/"])
    fun help() = "Append Country code in the URL"

    @GetMapping(value = ["/{countryCode}"])
    fun getGdp(@PathVariable countryCode: String) : String {
        return worldBankService.getGdp(countryCode)
    }
}
