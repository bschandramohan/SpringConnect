package com.bschandramohan.learn.springconnect.springdata.api

import com.bschandramohan.learn.springconnect.springdata.domain.Link
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import kotlin.random.Random

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

    @Test
    fun testGetLinkByPatron() {
        // Setup
        val user1 = Random(System.currentTimeMillis()).nextLong().toString()
        val user2 = Random(System.currentTimeMillis() * 3).nextLong().toString()
        val sampleLink = Link(null, user1, "https://sireesha.com", "Sireesha's URL")

        // 2 links for patron 234
        restTemplate.postForObject(linkApiEndpoint, sampleLink, Link::class.java)
        restTemplate.postForObject(
            linkApiEndpoint,
            sampleLink.apply { this.url = "https://www.google.com" },
            Link::class.java
        )

        // 1 link for patron 345
        restTemplate.postForObject(linkApiEndpoint, sampleLink.apply { this.patronId = user2 }, Link::class.java)

        // Execute
        var linkList = restTemplate.getForObject("$linkApiEndpoint/search?patronId=$user1", MutableList::class.java)
        // Verify
        assert(linkList.isNotEmpty())
        assert(linkList.size == 2)

        // Execute
        linkList = restTemplate.getForObject("$linkApiEndpoint/search?patronId=$user2", MutableList::class.java)
        // Verify
        assert(linkList.isNotEmpty())
        assert(linkList.size == 1)
    }
}
