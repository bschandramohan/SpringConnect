package com.bschandramohan.learn.springconnect.springbootconsole

import com.bschandramohan.learn.springconnect.springbootconsole.entity.Course
import com.bschandramohan.learn.springconnect.springbootconsole.entity.Student
import com.bschandramohan.learn.springconnect.springbootconsole.repository.StudentRepository
import com.bschandramohan.learn.springconnect.springbootconsole.service.CourseService
import com.bschandramohan.learn.springconnect.springbootconsole.service.StudentServiceImpl
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UnitTests {
    @Mock
    private lateinit var courseService: CourseService

    @Mock
    private lateinit var studentRepository: StudentRepository

    @InjectMocks
    private lateinit var studentService: StudentServiceImpl

    val student1ToSave = Student(1L, "Chandra")
    val student2ToSave = Student(2L, "Mohan")
    val courseToAdd = Course(1000L, "ML Course")

    @Test
    fun testGetStudentWithoutCourses() {
        Mockito.`when`(studentRepository.get(anyLong())).thenReturn(student1ToSave)

        studentService.save(student1ToSave)
        val result = studentService.get(1L)

        assert(result.name == "Chandra")
        assert(result.courses.size == 0)
    }

    @Test
    fun testGetStudentWithCourses() {
        Mockito.`when`(studentRepository.get(anyLong())).thenReturn(
            student2ToSave.apply { courses = mutableListOf(courseToAdd) })
        Mockito.`when`(courseService.list()).thenReturn(listOf(courseToAdd))

        studentService.save(student2ToSave)
        studentService.addCourse(2L, 1000L)

        val result = studentService.get(2L)
        assert(result.courses.size == 1)
        assert(result.courses[0].name == courseToAdd.name)
    }
}