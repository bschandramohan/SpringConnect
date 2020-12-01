package com.bschandramohan.learn.springconnect.springbootconsole.service

import com.bschandramohan.learn.springconnect.springbootconsole.entity.Student

interface StudentService {
    fun get(id: Long) : Student
    fun list() : List<Student>
    fun save(student: Student) : Student
    fun addCourse(id: Long, courseId: Long)
}