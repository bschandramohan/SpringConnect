package com.bschandramohan.learn.springconnect.springbootconsole.repository

import com.bschandramohan.learn.springconnect.springbootconsole.entity.Course

interface CourseRepository {
    fun get(id: Long) : Course
    fun list() : List<Course>
    fun save(course: Course): Course
}
