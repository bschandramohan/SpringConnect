package com.bschandramohan.learn.springconnect.springreactiveredis.repository

import com.bschandramohan.learn.springconnect.springreactiveredis.aop.TimeIt
import com.bschandramohan.learn.springconnect.springreactiveredis.domain.Link
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.time.Duration

@Repository
class LinkRepository(var redisTemplateForLink : ReactiveRedisTemplate<String, Link>) {
    val logger: Logger = LoggerFactory.getLogger(LinkRepository::class.java)
    val linkRedisOps = redisTemplateForLink.opsForValue()

    @TimeIt
    fun save(link: Link): Mono<Boolean> {
        val id: String = link.id ?: throw Exception("Invalid link id")
        return linkRedisOps.set(id, link, Duration.ofMinutes(10))
    }

    @TimeIt
    fun get(id: String) = linkRedisOps.get(id)

    @TimeIt
    fun getAll(): MutableList<Link>? {
//        val linkList = mutableListOf<Link>()
//        redisTemplateForLink.keys("*")
//            .flatMap(linkRedisOps::get)
//            .subscribe {
//                val link = linkRedisOps.get(it).block(Duration.ofMinutes(1))
//                if (link != null) {
//                    logger.info("Link=$link")
//                    linkList.add(link)
//                } else {
//                    logger.info("Link with id=$it expired while fetching details of all links")
//                }
//            }

        return redisTemplateForLink.keys("*")
            .flatMap(linkRedisOps::get)
            .collectList()
            .block(Duration.ofMinutes(1))
    }
}
