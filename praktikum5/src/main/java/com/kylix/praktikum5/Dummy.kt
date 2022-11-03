package com.kylix.praktikum5

object Dummy {
    fun getDummyStudents() = mutableListOf<Student>(
        Student("123456789012345", "John Doe", "PSI-A"),
        Student("123456789012346", "Jane Doe", "PSI-B"),
        Student("123456789012348", "Jane Smith", "PSI-D"),
    )
}