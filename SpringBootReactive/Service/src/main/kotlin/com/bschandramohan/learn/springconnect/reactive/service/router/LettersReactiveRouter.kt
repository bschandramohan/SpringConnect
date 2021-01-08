package com.bschandramohan.learn.springconnect.reactive.service.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

/**
 * Router way of implementing reactive web service in Spring
 *
 * Reference: https://spring.io/guides/gs/reactive-rest-service/
 */
@Configuration
class LettersReactiveRouter {
    @Bean
    fun route(lettersReactiveHandler: LettersReactiveHandler) : RouterFunction<ServerResponse> {
        return RouterFunctions.route(RequestPredicates.GET("/router/vowels"), lettersReactiveHandler::vowels)
            .andRoute(RequestPredicates.GET("/router/consonants"), lettersReactiveHandler::consonants)
    }
}
