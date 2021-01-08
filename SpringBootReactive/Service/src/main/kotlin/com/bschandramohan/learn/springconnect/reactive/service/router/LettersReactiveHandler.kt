package com.bschandramohan.learn.springconnect.reactive.service.router

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class LettersReactiveHandler {
    fun vowels(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().body(BodyInserters.fromValue(
            com.bschandramohan.learn.springconnect.reactive.service.repository.vowels()))
    }

    fun consonants(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().body(BodyInserters.fromValue(
            com.bschandramohan.learn.springconnect.reactive.service.repository.consonants()))
    }
}
