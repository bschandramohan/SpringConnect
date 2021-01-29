package com.bschandramohan.learn.springconnect.springdatareactive.api

import com.bschandramohan.learn.springconnect.springdatareactive.configuration.ApiServerError
import com.bschandramohan.learn.springconnect.springdatareactive.domain.Link
import com.bschandramohan.learn.springconnect.springdatareactive.service.LinkService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("link")
class LinkApi(var linkService: LinkService) {

    @PostMapping("/")
    fun create(@RequestBody link: Link): ResponseEntity<Any> {
        return try {
            ResponseEntity(linkService.create(link), HttpStatus.CREATED)
        } catch (e: Exception) {
            ApiServerError("Link", "create", e)
        }
    }

    @PutMapping("/")
    fun update(@RequestBody link: Link): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(linkService.update(link))
        } catch (e: Exception) {
            ApiServerError("Link", "update", e)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Any> {
        return try {
            linkService.delete(id)
            ResponseEntity.ok(true)
        } catch (e: Exception) {
            ApiServerError("Link", "delete", e)
        }
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: String): ResponseEntity<Any> {
        return try {
            val result = linkService.get(id)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ApiServerError("Link", "get", e)
        }
    }

    @GetMapping("/")
    fun list(): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(linkService.list())
        } catch (e: Exception) {
            ApiServerError("Link", "List", e)
        }
    }

    @GetMapping("/search")
    fun search(@RequestParam userId: String): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(linkService.findByUser(userId))
        } catch (e: Exception) {
            ApiServerError("Link", "get", e)
        }
    }
}
