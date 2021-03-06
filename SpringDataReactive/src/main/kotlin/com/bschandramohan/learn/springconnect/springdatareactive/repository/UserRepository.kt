package com.bschandramohan.learn.springconnect.springdatareactive.repository

import com.bschandramohan.learn.springconnect.springdatareactive.domain.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface UserRepository : ReactiveMongoRepository<User, String> {
    fun findByName(name: String): Mono<User>
}
