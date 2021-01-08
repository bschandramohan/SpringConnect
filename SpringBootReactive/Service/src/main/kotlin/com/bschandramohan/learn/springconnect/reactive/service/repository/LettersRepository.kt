package com.bschandramohan.learn.springconnect.reactive.service.repository

// Well... we are not here to learn design patterns of provider,service,repository etc.
// Just mimic functions that returns data
fun vowels() = listOf('A', 'E', 'I', 'O', 'U')

fun consonants() = ('A'..'Z').toList().filter { it ->  it !in vowels() }
