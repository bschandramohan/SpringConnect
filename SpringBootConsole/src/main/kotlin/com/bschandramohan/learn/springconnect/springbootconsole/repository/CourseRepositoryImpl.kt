package com.bschandramohan.learn.springconnect.springbootconsole.repository

import com.bschandramohan.learn.springconnect.springbootconsole.entity.Course
import org.springframework.stereotype.Repository

@Repository
class CourseRepositoryImpl : CourseRepository {
    // FOR DEMO - Storing the data in memory
    var courseDatabase = mutableMapOf<Long, Course>()

    override fun get(id: Long) = courseDatabase[id] ?: throw Exception("ID doesn't exist")

    override fun list(): List<Course> = courseDatabase.values.toList()

    override fun save(course: Course): Course {

        courseDatabase[course.id] = course
        return courseDatabase[course.id] ?: throw Exception("Could not save course")
    }
}