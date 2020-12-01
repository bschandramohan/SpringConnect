package com.bschandramohan.learn.springconnect.springbootconsole.repository

import com.bschandramohan.learn.springconnect.springbootconsole.entity.Student
import org.springframework.stereotype.Repository

@Repository
class StudentRepositoryImpl : StudentRepository {
    // For demo purpose, storing in in-memory variable
    var studentDatabase = mutableMapOf<Long, Student>()

    override fun get(id: Long): Student = studentDatabase[id] ?: throw Exception("Student with ID doesn't exist")

    override fun list(): List<Student> = studentDatabase.values.toList()

    override fun save(student: Student): Student {
        studentDatabase[student.id] = student
        return studentDatabase[student.id] ?: throw Exception("Error in student save")
    }
}