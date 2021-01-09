package com.bschandramohan.learn.springconnect.springdata.api

import com.bschandramohan.learn.springconnect.springdata.domain.Patron
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PatronApiTest(@Autowired var restTemplate: TestRestTemplate, @LocalServerPort port: Int) {

    val patronApiEndpoint = "http://localhost:8080/patron/"

    @Test
    fun testCreateAndGetPatron() {
        val samplePatron = Patron(null, "chandra", "chandra@sireesha.com", 6506506506)

        val savedPatron = restTemplate.postForObject(patronApiEndpoint, samplePatron, Patron::class.java)
        assert(savedPatron != null)

        val retrievedPatron = restTemplate.getForObject("${patronApiEndpoint}${savedPatron.id}", Patron::class.java)
        assert(retrievedPatron != null)
    }
}
