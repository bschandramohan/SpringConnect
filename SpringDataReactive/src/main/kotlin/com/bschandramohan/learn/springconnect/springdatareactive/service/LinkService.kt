package com.bschandramohan.learn.springconnect.springdatareactive.service

import com.bschandramohan.learn.springconnect.springdatareactive.domain.Link
import com.bschandramohan.learn.springconnect.springdatareactive.repository.LinkRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class LinkService(var linkRepository: LinkRepository) {
    fun create(link: Link) = linkRepository.save(link)

    fun update(link: Link): Mono<Link> {
        // For simplicity reason, let's assume we are not checking if it's already present
        // and we don't support partial merge of updates
        return linkRepository.save(link)
    }

    fun delete(id: String) {
        linkRepository.deleteById(id)
    }

    fun get(id: String) = linkRepository.findById(id)

    fun list(): Flux<Link> = linkRepository.findAll()

    fun findByUser(userId: String) = linkRepository.findByUserId(userId)
}
