package com.bschandramohan.learn.springconnect.springdatareactive.api

import com.bschandramohan.learn.springconnect.springdatareactive.domain.Link
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import kotlin.random.Random

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LinkApiTest {
    val linkApiPath = "/link/"
    val sampleLink = Link(null, "123", "https://www.google.com", "Useful Link")

    val logger = LoggerFactory.getLogger(LinkApiTest::class.java)

    @Test
    fun testCreateAndGetLink() {
        var savedLink = postMonoResult(linkApiPath, sampleLink, Link::class.java)
        savedLink = savedLink ?: throw Exception ("Saved Link cannot be null")
        assert(savedLink.id != null) { "Saved Link ID cannot be null" }
        assert(savedLink.url == sampleLink.url) { "Saved Link url should match the sender" }

        var retrievedLink = getMonoResult("${linkApiPath}${savedLink.id}", Link::class.java)
        retrievedLink = retrievedLink ?: throw Exception ("Retrieved Link cannot be null")
        assert(retrievedLink.id != null) { "Retrieved Link ID cannot be null" }
        assert(retrievedLink.url == sampleLink.url) { "Retrieved Link url should match the sender" }
    }

    @Test
    fun testGetLinkByUser() {
        // Setup
        val user1 = Random(System.currentTimeMillis()).nextLong().toString()
        val user2 = Random(System.currentTimeMillis() * 3).nextLong().toString()
        val sampleLink = Link(null, user1, "https://www.random${Random.nextLong()}.com", "Favorite URL")

        // 2 links for user1
        postMonoResult(linkApiPath, sampleLink, Link::class.java)
        postMonoResult(linkApiPath, sampleLink.apply { this.url = "https://www.${Random.nextLong()}random.com" }, Link::class.java)

        // 1 link for user2
        postMonoResult(linkApiPath, sampleLink.apply { this.userId = user2 }, Link::class.java)

        // Execute
        var linkList = getFluxResult("${linkApiPath}search?userId=$user1", Link::class.java)
        // Verify
        linkList = linkList ?: throw Exception ("Links List cannot be null")
        assert(linkList.isNotEmpty())
        assert(linkList.size == 2)

        // Execute
        linkList = getFluxResult("${linkApiPath}search?userId=$user2", Link::class.java)
        // Verify
        linkList = linkList ?: throw Exception ("Links List cannot be null")
        assert(linkList.isNotEmpty())
        assert(linkList.size == 1)
    }

    @Test
    fun testErrorResponse() {
        val errorResult: Exception = assertThrows {
            postMonoResult("$linkApiPath/dummy", sampleLink, Link::class.java)
        }

        errorResult.message?.contains("Status=405")?.let { assert(it) }
    }
}
