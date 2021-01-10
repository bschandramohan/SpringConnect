package com.bschandramohan.learn.springconnect.springdatareactive.service

import com.bschandramohan.learn.springconnect.springdatareactive.domain.User
import com.bschandramohan.learn.springconnect.springdatareactive.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserService(var userRepository: UserRepository) {
    fun create(user: User) = userRepository.save(user)

    fun update(user: User): Mono<User> {
        // For simplicity reason, let's assume we are not checking if it's already present
        // and we don't support partial merge of updates
        return userRepository.save(user)
    }

    fun delete(id: String) {
        userRepository.deleteById(id)
    }

    fun get(id: String) = userRepository.findById(id)

    fun list(): Flux<User> = userRepository.findAll()

    fun getByName(name: String) = userRepository.findByName(name)

    fun deleteByName(name: String) {
        val toDeleteEntity = getByName(name)
        delete(toDeleteEntity.block()?.id ?: "")
    }
}
