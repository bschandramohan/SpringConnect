package com.bschandramohan.learn.springconnect.springdatareactive.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * User who supports humanity by sharing useful/entertaining links.
 */
@Document(collection = "user")
data class User(
    @Id
    var id: String?,
    var name: String?,
    var email: String?,
    var phoneNumber: Number?
)
