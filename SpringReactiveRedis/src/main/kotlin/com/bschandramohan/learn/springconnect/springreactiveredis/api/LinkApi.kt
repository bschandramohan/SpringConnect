package com.bschandramohan.learn.springconnect.springreactiveredis.api

import com.bschandramohan.learn.springconnect.springreactiveredis.aop.TimeIt
import com.bschandramohan.learn.springconnect.springreactiveredis.domain.Link
import com.bschandramohan.learn.springconnect.springreactiveredis.error.ApiServerError
import com.bschandramohan.learn.springconnect.springreactiveredis.error.EntityNotFoundError
import com.bschandramohan.learn.springconnect.springreactiveredis.service.LinkService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("link")
class LinkApi(var linkService: LinkService) {
    val logger: Logger = LoggerFactory.getLogger(LinkApi::class.java)
    val entityName = "Link"

    @TimeIt
    @PostMapping("")
    fun save(@RequestBody link: Link) : ResponseEntity<Any> =
        try {
            ResponseEntity(linkService.save(link), HttpStatus.CREATED)
        } catch (e: Exception) {
            logger.error("$entityName save failed", e)
            ApiServerError(entityName, "save", e)
        }

    @TimeIt
    @GetMapping("/{id}")
    fun get(@PathVariable id: String) : ResponseEntity<Any> =
        try {
            val link = linkService.get(id).block()
            if (link != null) {
                ResponseEntity(link, HttpStatus.FOUND)
            } else {
                EntityNotFoundError(entityName)
            }
        } catch (e: Exception) {
            logger.error("$entityName get failed", e)
            ApiServerError(entityName, "get", e)
        }

    @TimeIt
    @GetMapping("")
    fun get(): ResponseEntity<Any> =
        try {
            var list = linkService.getAll()
            if (list.isNullOrEmpty()) {
                list = mutableListOf()
            }
            ResponseEntity.ok(list)

        } catch (e: Exception) {
            logger.error("$entityName getAll failed", e)
            ApiServerError(entityName, "getAll", e)
        }
}