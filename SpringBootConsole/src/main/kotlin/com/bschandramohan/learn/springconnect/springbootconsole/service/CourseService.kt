package com.bschandramohan.learn.springconnect.springbootconsole.service

import com.bschandramohan.learn.springconnect.springbootconsole.entity.Course

interface CourseService {
    fun get(id: Long) : Course
    fun list() : List<Course>
    fun save(course: Course): Course
}
