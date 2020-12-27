package com.bschandramohan.learn.springconnect.springbootsampleprojects.worldbankdata

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WorldBankDataApplication

fun main(args: Array<String>) {
    runApplication<WorldBankDataApplication>(*args)
}
