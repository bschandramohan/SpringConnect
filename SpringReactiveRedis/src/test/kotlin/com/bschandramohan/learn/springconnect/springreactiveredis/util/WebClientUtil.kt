package com.bschandramohan.learn.springconnect.springreactiveredis.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.lang.Exception
import java.time.Duration

class WebClientUtil

val logger: Logger = LoggerFactory.getLogger(WebClientUtil::class.java)
val webClient = WebClient.create("http://localhost:8080/link")

inline fun <reified T: Any> postMonoResult(body: T) =
    webClient.post()
        .uri("")
        .body(BodyInserters.fromValue(body))
        .retrieve()
        .onStatus(HttpStatus::isError, ::renderErrorResponse)
        .bodyToMono(Boolean::class.java)
        .block(Duration.ofMinutes(2))

inline fun <reified T: Any> getMonoResult(id: String) =
    webClient.get()
        .uri("/$id")
        .retrieve()
        .onStatus(HttpStatus::isError, ::renderErrorResponse)
        .bodyToMono(T::class.java)
        .block(Duration.ofMinutes(2))

inline fun <reified T: Any> getFluxResult(): MutableList<T>? =
    webClient.get()
        .uri("")
        .retrieve()
        .onStatus(HttpStatus::isError, ::renderErrorResponse)
        .bodyToFlux(T::class.java)
        .collectList()
        .block(Duration.ofMinutes(2))

fun renderErrorResponse(clientResponse: ClientResponse) : Mono<Throwable> {
    logger.error("Error on call. StatusCode=${clientResponse.statusCode()}")

    if (clientResponse.statusCode().is3xxRedirection) {
        return Mono.error(Exception("Redirection called for ${clientResponse.headers().header("Location")[0]}"))
    } else if (clientResponse.statusCode().is4xxClientError) {
        return Mono.error(Exception("Invalid client call. Status=${clientResponse.statusCode()}"))
    }
    return Mono.error(Exception("Server error. Status=${clientResponse.statusCode()}"))
}
