package com.bschandramohan.learn.springconnect.springdata.api

import com.bschandramohan.learn.springconnect.springdata.domain.Link
import com.bschandramohan.learn.springconnect.springdata.service.LinkService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException

@RestController
@RequestMapping("link")
class LinkApi(var linkService: LinkService) {

    @PostMapping("/")
    fun create(@RequestBody link: Link): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(linkService.create(link))
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
            ResponseEntity.ok(linkService.delete(id))
        } catch (e: Exception) {
            ApiServerError("Link", "delete", e)
        }
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: String): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(linkService.get(id))
        } catch (e: Exception) {
            if (e is EntityNotFoundException || e.cause is EntityNotFoundException)
                ApiNotFoundError("Link")
            else
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
}
