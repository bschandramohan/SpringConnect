package com.bschandramohan.learn.springconnect.springbootconsole.repository

import com.bschandramohan.learn.springconnect.springbootconsole.entity.Student

interface StudentRepository {
    fun get(id: Long): Student
    fun list(): List<Student>
    fun save(student: Student): Student
}