package com.bschandramohan.learn.springconnect.springbootconsole.entity

data class Course(var id: Long, var name: String)

data class Student(var id: Long, var name: String, var courses: MutableList<Course> = mutableListOf())
