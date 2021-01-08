package com.bschandramohan.learn.springconnect.reactive.client

import org.springframework.web.reactive.function.client.WebClient

class ClientApplication {
    private val client = WebClient.create("http://localhost:8080")

    fun getResult(uriParam: String) = client.get()
        .uri(uriParam)
        .retrieve().bodyToMono(String::class.java).block()
}

fun main(args: Array<String>) {
    println("Controller (Annotation based) Vowels Result=${ClientApplication().getResult("/controller/vowels")}")
    println("Controller (Annotation based) Consonants Result=${ClientApplication().getResult("/controller/consonants")}")

    println("Router Vowels Result=${ClientApplication().getResult("/router/vowels")}")
    println("Router Consonants Result=${ClientApplication().getResult("/router/consonants")}")
}
