package com.bschandramohan.learn.springconnect.springreactiveredis.service

import com.bschandramohan.learn.springconnect.springreactiveredis.domain.Link
import com.bschandramohan.learn.springconnect.springreactiveredis.repository.LinkRepository
import org.springframework.stereotype.Service

@Service
class LinkService(var linkRepository: LinkRepository) {
    fun save(link: Link) = linkRepository.save(link)
    fun get(id: String) = linkRepository.get(id)
    fun getAll() = linkRepository.getAll()
}
