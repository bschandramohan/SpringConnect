package com.bschandramohan.learn.springbatch

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Class defined to Just launch the spring boot application. Batch Jobs are auto configured and executed via Annotations.
 */
@SpringBootApplication
open class Application

fun main() {
    SpringApplication.run(Application::class.java)
}