package com.bschandramohan.learn.springconnect.springdata.api

import com.bschandramohan.learn.springconnect.springdata.domain.Patron
import com.bschandramohan.learn.springconnect.springdata.service.PatronService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException

@RestController
@RequestMapping("patron")
class PatronApi(var patronService: PatronService) {
    @PostMapping("/")
    fun create(@RequestBody patron: Patron): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(patronService.create(patron))
        } catch (e: Exception) {
            ApiServerError("Patron", "create", e)
        }
    }

    @PutMapping("/")
    fun update(@RequestBody patron: Patron): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(patronService.update(patron))
        } catch (e: Exception) {
            ApiServerError("Patron", "update", e)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(patronService.delete(id))
        } catch (e: Exception) {
            ApiServerError("Patron", "delete", e)
        }
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: String): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(patronService.get(id))
        } catch (e: Exception) {
            if (e is EntityNotFoundException || e.cause is EntityNotFoundException)
                ApiNotFoundError("Patron")
            else
                ApiServerError("Patron", "get", e)
        }
    }

    @GetMapping("/")
    fun list(): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(patronService.list())
        } catch (e: Exception) {
            ApiServerError("Patron", "List", e)
        }
    }

    @GetMapping("/search")
    fun getByName(@RequestParam name: String): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(patronService.getByName(name))
        } catch (e: Exception) {
            if (e is EntityNotFoundException || e.cause is EntityNotFoundException)
                ApiNotFoundError("Patron")
            else
                ApiServerError("Patron", "get", e)
        }
    }

    @DeleteMapping("/delete")
    fun deleteByName(@RequestParam name: String): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(patronService.deleteByName(name))
        } catch (e: Exception) {
            if (e is EntityNotFoundException || e.cause is EntityNotFoundException)
                ApiNotFoundError("Patron")
            else
                ApiServerError("Patron", "List", e)
        }
    }
}
