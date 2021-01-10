package com.bschandramohan.learn.springconnect.springdata.repository

import com.bschandramohan.learn.springconnect.springdata.domain.Link
import org.springframework.data.mongodb.repository.MongoRepository

interface LinkRepository : MongoRepository<Link, String>
