package com.bschandramohan.learn.springconnect.springreactiveredis.domain

/**
 * Share Links useful for others
 */
data class Link(
    var id: String? = "",
    var userId: String? = "",
    var url: String? = null,
    var description: String? = null
)
