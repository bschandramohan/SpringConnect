package com.bschandramohan.learn.springconnect.springdatareactive.api

import com.bschandramohan.learn.springconnect.springdatareactive.domain.User
import org.junit.jupiter.api.Test

import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserApiTest {
    val userApiPath = "/user/"

    @Test
    fun testCreateAndGetUser() {
        val sampleUser = User(null, "chandra", "chandra@sireesha.com", 6506506506)

        var savedUser = postMonoResult(userApiPath, sampleUser, User::class.java)
        savedUser = savedUser ?: throw Exception("Saved User cannot be null")
        assert(savedUser.id != null) { "Saved User ID cannot be null" }
        assert(savedUser.name == sampleUser.name) { "Saved User name should match the sender" }

        var retrievedUser = getMonoResult("${userApiPath}${savedUser.id}", User::class.java)
        retrievedUser = retrievedUser ?: throw Exception("Retrieved User ID cannot be null" )
        assert(retrievedUser.name == sampleUser.name) { "Retrieved User name should match the sender" }
    }
}
