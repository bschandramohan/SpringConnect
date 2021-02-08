package com.bschandramohan.learn.springconnect.springreactiveredis.integrationtests

import com.bschandramohan.learn.springconnect.springreactiveredis.domain.Link
import com.bschandramohan.learn.springconnect.springreactiveredis.util.getFluxResult
import com.bschandramohan.learn.springconnect.springreactiveredis.util.getMonoResult
import com.bschandramohan.learn.springconnect.springreactiveredis.util.postMonoResult
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.*
import java.util.concurrent.ThreadLocalRandom

@SpringBootTest
class LinkApiTest {
    var testUrl = "https://medium.com/techieconnect"
    fun testLink() = Link(UUID.randomUUID().toString(), ThreadLocalRandom.current().nextLong(100000).toString(),
        testUrl, "Helpful resources for backend developers")

    @Test
    fun testCreateAndGetLink_withValidValues_ReturnsLink() {
        val link = testLink()
        val success = postMonoResult(link)
        assert(success == true)

        val resultLink = getMonoResult<Link>(link.id!!)
        assert(resultLink != null)
        assert (resultLink?.url == testUrl)
    }

    @Test
    fun testGetAll_withTwoCreations_ReturnsTwoAdditional() {
        val originalLinkList = getFluxResult<Link>()
        val originalListCount = originalLinkList?.size ?: 0

        val link = testLink()
        val success = postMonoResult(link)
        assert(success == true)

        val newLinkList = getFluxResult<Link>()
        val newListCount = newLinkList?.size ?: 0
        assert(newListCount == originalListCount + 1)
    }
}
