package com.bschandramohan.learn.springconnect.springbootweb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class SpringBootWebApplication

fun main(args: Array<String>) {
    runApplication<SpringBootWebApplication>(*args)
}
