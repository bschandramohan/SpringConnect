package com.bschandramohan.learn.springconnect.springdata.api

import com.bschandramohan.learn.springconnect.springdata.domain.Link
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LinkApiTest(@Autowired var restTemplate: TestRestTemplate, @LocalServerPort port: Int) {

    val linkApiEndpoint = "http://localhost:8080/link/"

    @Test
    fun testCreateAndGetLink() {
        val sampleLink = Link(null, "123", "https://sireesha.com", "Sireesha's URL")

        val savedLink = restTemplate.postForObject(linkApiEndpoint, sampleLink, Link::class.java)
        assert(savedLink != null) { "Saved Link cannot be null" }
        assert(savedLink.id != null) { "Saved Link ID cannot be null" }
        assert(savedLink.url == sampleLink.url) { "Saved Link url should match the sender" }

        val retrievedLink = restTemplate.getForObject("${linkApiEndpoint}${savedLink.id}", Link::class.java)
        assert(retrievedLink != null)
        assert(retrievedLink.id != null) { "Retrieved Link ID cannot be null" }
        assert(retrievedLink.url == sampleLink.url) { "Retrieved Link url should match the sender" }
    }
}
