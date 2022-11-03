package com.kylix.praktikum5

import com.kylix.praktikum5.base.BaseDiffUtil

class StudentDiffUtil(
    private val oldList: List<Student>,
    private val newList: List<Student>,
) : BaseDiffUtil<Student, String>(oldList, newList) {
    override fun Student.getItemIdentifier(): String = id
}
