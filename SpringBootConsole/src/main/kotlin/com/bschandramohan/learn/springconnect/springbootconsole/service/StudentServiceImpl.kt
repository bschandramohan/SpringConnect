package com.bschandramohan.learn.springconnect.springbootconsole.service

import com.bschandramohan.learn.springconnect.springbootconsole.entity.Course
import com.bschandramohan.learn.springconnect.springbootconsole.entity.Student
import com.bschandramohan.learn.springconnect.springbootconsole.repository.StudentRepository
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl(var studentRepository: StudentRepository, var courseService: CourseService) : StudentService {
    override fun get(id: Long): Student {
        val student = studentRepository.get(id)

        val courseList = courseService.list()
        mapCourses(student, courseList)

        return student
    }

    private fun mapCourses(student: Student, courseList: List<Course>) {
        student.courses.map {
            if (it.name.isEmpty()) {
                it.name = courseList.first { course -> course.id == it.id }.name
            }
        }
    }

    override fun list(): List<Student> {
        val studentList = studentRepository.list()
        val courseList = courseService.list()

        studentList.forEach {
            student -> mapCourses(student, courseList)
        }

        return studentList
    }

    override fun save(student: Student): Student {
        studentRepository.save(student)
        return studentRepository.get(student.id)
    }

    override fun addCourse(id: Long, courseId: Long) {
        val student = studentRepository.get(id)

        // Check if course is already added
        val courseAlreadyAdded = student.courses.firstOrNull { it.id == courseId }
        if (courseAlreadyAdded == null) {
            // If not already added, then add it after fetching the course name
            val courseList = courseService.list()
            val correspondingCourse = courseList.firstOrNull { it.id == courseId }
                ?: throw Exception("Course ID passed is invalid")
            student.courses.add(correspondingCourse)
        }
    }
}
