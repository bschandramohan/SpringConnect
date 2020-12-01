package com.bschandramohan.learn.springconnect.springbootconsole.service

import com.bschandramohan.learn.springconnect.springbootconsole.entity.Course
import com.bschandramohan.learn.springconnect.springbootconsole.repository.CourseRepository
import org.springframework.stereotype.Service

@Service
class CourseServiceImpl(var courseRepository: CourseRepository) : CourseService {
    override fun get(id: Long): Course = courseRepository.get(id)

    override fun list(): List<Course> = courseRepository.list()

    override fun save(course: Course): Course = courseRepository.save(course)
}