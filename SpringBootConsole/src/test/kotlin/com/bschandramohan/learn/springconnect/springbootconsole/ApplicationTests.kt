package com.bschandramohan.learn.springconnect.springbootconsole

import com.bschandramohan.learn.springconnect.springbootconsole.entity.Course
import com.bschandramohan.learn.springconnect.springbootconsole.entity.Student
import com.bschandramohan.learn.springconnect.springbootconsole.service.CourseService
import com.bschandramohan.learn.springconnect.springbootconsole.service.StudentService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.lang.Exception

@SpringBootTest
class ApplicationTests(@Autowired var courseService: CourseService, @Autowired var studentService: StudentService) {
    @Test
    fun testCourseGet() {
        assertThrows<Exception> {
            courseService.get(1)
        }
    }

    @Test
    fun testCourseSaveAndGetList() {
        courseService.save(mlCourse)

        val result = courseService.get(1001L)
        assert(result.name == mlCourse.name)

        courseService.save(kubernetesCourse)
        val results = courseService.list()
        assert(results.isNotEmpty())
        assert(results.size == 2)
    }

    @Test
    fun testStudentGet() {
        assertThrows<Exception> {
            studentService.get(1001L)
        }
    }

    @Test
    fun testStudentSaveAndGetList() {
        studentService.save(studentChandra)

        assert(studentService.get(1001L).name == studentChandra.name)

        studentService.save(studentMohan)

        val results = studentService.list()

        assert(results.size == 2)

        studentService.addCourse(1001L, 1001L)
        studentService.addCourse(1001L, 1002L)
        studentService.addCourse(1002L, 1001L)

        val updatedList = studentService.list()
        // We could do filter on course[id] if we do not want the assumption of order of returns
        assert(updatedList[0].courses.size == 2)
        assert(updatedList[1].courses.size == 1)

        assertThrows<Exception> {
            // Course 1000L doesn't exist
            studentService.addCourse(1002L, 1000L)
        }
    }

    // some test data
    private val mlCourse = Course(1001L, "Machine Learning")
    private val kubernetesCourse = Course(1002L, "Kubernetes")

    private val studentChandra = Student(1001L, "Chandra")
    private val studentMohan = Student(1002L, "Mohan")
}
