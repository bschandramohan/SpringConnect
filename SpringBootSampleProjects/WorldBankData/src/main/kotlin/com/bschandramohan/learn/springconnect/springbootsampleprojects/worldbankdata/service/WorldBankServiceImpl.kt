package com.bschandramohan.learn.springconnect.springbootsampleprojects.worldbankdata.service

import com.bschandramohan.learn.springconnect.springbootsampleprojects.worldbankdata.domain.WorldBankData
import org.springframework.stereotype.Service
import java.net.URL
import java.time.LocalDate
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable

@Service
class WorldBankServiceImpl : WorldBankService {
    val logger = LoggerFactory.getLogger(WorldBankServiceImpl::class.java)
    // Registering kotlin module is a required step as listed in https://www.baeldung.com/jackson-kotlin
    // else returns:
    // kotlin jackson (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
    val mapper = ObjectMapper().registerModule(KotlinModule())

    val previousYear = LocalDate.now().year - 1

    @Cacheable("CountryGdp")
    override fun getGdp(countryCode: String): String {
        val url = "http://api.worldbank.org/v2/countries/$countryCode/indicators/NY.GDP.MKTP.CD?format=json&date=$previousYear"
        val response = URL(url).readText()
        val startIndex = response.indexOf("{\"indicator\"")
        val mappedResponse = response.substring(startIndex, response.length - 2)

        val worldBankData = mapper.readValue<WorldBankData>(mappedResponse)
        return "${worldBankData.country.value}'s GDP for year=$previousYear is value=$${"%,d".format(worldBankData.value)}"
    }
}
