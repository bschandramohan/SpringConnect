package com.bschandramohan.learn.springconnect.springdata.repository

import com.bschandramohan.learn.springconnect.springdata.domain.Patron
import org.springframework.data.jpa.repository.JpaRepository

interface PatronRepository : JpaRepository<Patron, String> {
    fun findByName(name: String): Patron
    fun findByNameIgnoreCase(name: String): Patron
}
