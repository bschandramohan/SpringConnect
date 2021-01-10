package com.bschandramohan.learn.springconnect.springdatareactive.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Share Links useful for others
 */
@Document(collection = "link")
data class Link(
    @Id
    var id: String?,
    var userId: String?,
    var url: String?,
    var description: String?
)
