package com.kylix.praktikum5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _students: MutableLiveData<MutableList<Student>> = MutableLiveData()
    val students: LiveData<MutableList<Student>> = _students
    
    init {
        _students.value = Dummy.getDummyStudents()
    }
    
    fun setNewStudent(id: String, name: String, classroom: String) {
        val newStudent = Student(id, name, classroom)
        _students.value = _students.value?.apply {
            add(newStudent)
        }
    }
}