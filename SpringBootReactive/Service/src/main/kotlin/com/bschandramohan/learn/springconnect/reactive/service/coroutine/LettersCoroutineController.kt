package com.bschandramohan.learn.springconnect.reactive.service.coroutine

import com.bschandramohan.learn.springconnect.reactive.service.repository.consonants
import com.bschandramohan.learn.springconnect.reactive.service.repository.vowels
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// METHOD 3: Using coroutine Flow to return data
@RestController
@RequestMapping("/coroutine")
class LettersCoroutineController {

    @GetMapping("/vowels")
    suspend fun getVowels() : Flow<Char> = vowels().asFlow()

    @GetMapping("/consonants")
    suspend fun getConsonants() : Flow<Char> = consonants().asFlow()
}
