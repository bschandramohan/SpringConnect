package com.bschandramohan.app.sharelinks.domain

import java.net.URL

data class Link(val id: String, val url: URL, val description: String, val owner: String)

