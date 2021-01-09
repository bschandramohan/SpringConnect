package com.bschandramohan.learn.springconnect.springdata.service

import com.bschandramohan.learn.springconnect.springdata.domain.Patron
import com.bschandramohan.learn.springconnect.springdata.repository.PatronRepository
import org.springframework.stereotype.Service

@Service
class PatronService(var patronRepository: PatronRepository) {
    fun create(patron: Patron) = patronRepository.save(patron)

    fun update(patron: Patron): Patron {
        // For simplicity reason, let's assume we are not checking if it's already present
        // and we don't support partial merge of updates
        return patronRepository.save(patron)
    }

    fun delete(id: String) {
        patronRepository.deleteById(id)
    }

    fun get(id: String) = patronRepository.getOne(id)

    fun list(): MutableList<Patron> = patronRepository.findAll()

    fun getByName(name: String) = patronRepository.findByName(name)

    fun deleteByName(name: String) {
        val toDeleteEntity = getByName(name)
        delete(toDeleteEntity.id ?: "")
    }
}
