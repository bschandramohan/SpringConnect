package com.bschandramohan.learn.springconnect.springdata.service

import com.bschandramohan.learn.springconnect.springdata.domain.Link
import com.bschandramohan.learn.springconnect.springdata.repository.LinkRepository
import org.springframework.stereotype.Service

@Service
class LinkService(var linkRepository: LinkRepository) {
    fun create(link: Link) = linkRepository.save(link)

    fun update(link: Link): Link {
        // For simplicity reason, let's assume we are not checking if it's already present
        // and we don't support partial merge of updates
        return linkRepository.save(link)
    }

    fun delete(id: String) {
        linkRepository.deleteById(id)
    }

    fun get(id: String) = linkRepository.findById(id)

    fun list(): MutableList<Link> = linkRepository.findAll()

    fun findByPatron(patronId: String) = linkRepository.findByPatronId(patronId)
}
