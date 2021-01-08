package com.bschandramohan.learn.springconnect.reactive.service.controller

import com.bschandramohan.learn.springconnect.reactive.service.repository.consonants
import com.bschandramohan.learn.springconnect.reactive.service.repository.vowels
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.kotlin.core.publisher.toFlux

// Method 2: Using Annotation way of creating reactive web service
@RestController
@RequestMapping("controller")
class LettersReactiveController {
    @GetMapping("/vowels")
    fun getVowels() : Flux<Char> {
        return vowels().toFlux()
    }

    @GetMapping("/consonants")
    fun getConsonants(): Flux<Char> {
        return consonants().toFlux()
    }
}
