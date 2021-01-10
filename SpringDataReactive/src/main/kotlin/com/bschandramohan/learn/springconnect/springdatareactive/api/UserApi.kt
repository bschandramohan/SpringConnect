package com.bschandramohan.learn.springconnect.springdatareactive.api

import com.bschandramohan.learn.springconnect.springdatareactive.domain.User
import com.bschandramohan.learn.springconnect.springdatareactive.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("user")
class UserApi(var userService: UserService) {
    @PostMapping("/")
    fun create(@RequestBody user: User): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(userService.create(user))
        } catch (e: Exception) {
            ApiServerError("User", "create", e)
        }
    }

    @PutMapping("/")
    fun update(@RequestBody user: User): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(userService.update(user))
        } catch (e: Exception) {
            ApiServerError("User", "update", e)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(userService.delete(id))
        } catch (e: Exception) {
            ApiServerError("User", "delete", e)
        }
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: String): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(userService.get(id))
        } catch (e: Exception) {
            ApiServerError("User", "get", e)
        }
    }

    @GetMapping("/")
    fun list(): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(userService.list())
        } catch (e: Exception) {
            ApiServerError("User", "List", e)
        }
    }

    @GetMapping("/search")
    fun getByName(@RequestParam name: String): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(userService.getByName(name))
        } catch (e: Exception) {
            ApiServerError("User", "get", e)
        }
    }

    @DeleteMapping("/delete")
    fun deleteByName(@RequestParam name: String): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(userService.deleteByName(name))
        } catch (e: Exception) {
            ApiServerError("User", "List", e)
        }
    }
}
