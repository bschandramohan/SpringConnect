package com.bschandramohan.learn.springconnect.springdata

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class SpringDataApplication

fun main(args: Array<String>) {
    runApplication<SpringDataApplication>(*args)
}
