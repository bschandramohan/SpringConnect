package com.bschandramohan.learn.springconnect.springdata.domain

import org.hibernate.annotations.GenericGenerator
import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Document(collection = "link")
data class Link(
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    var id: String?,
    var patronId: String?,
    var url: String?,
    var description: String?
)
