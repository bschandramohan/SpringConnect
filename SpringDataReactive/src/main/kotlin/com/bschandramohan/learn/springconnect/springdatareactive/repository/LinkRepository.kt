package com.bschandramohan.learn.springconnect.springdatareactive.repository

import com.bschandramohan.learn.springconnect.springdatareactive.domain.Link
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface LinkRepository : ReactiveMongoRepository<Link, String> {
    fun findByUserId(userId: String): Flux<Link>
}
