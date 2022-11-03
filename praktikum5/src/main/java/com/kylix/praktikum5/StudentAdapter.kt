package com.kylix.praktikum5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.kylix.praktikum5.base.BaseRecyclerViewAdapter
import com.kylix.praktikum5.databinding.ItemListStudentBinding

class StudentAdapter: BaseRecyclerViewAdapter<ItemListStudentBinding, Student>() {
    override fun inflateViewBinding(parent: ViewGroup): ItemListStudentBinding =
        ItemListStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    
    override val binder: (Student, ItemListStudentBinding) -> Unit = {data, binding ->
        binding.tvId.text = data.id
        binding.tvName.text = data.name
        binding.tvClassroom.text = data.classroom
    }
    
    override val diffUtilBuilder: (List<Student>, List<Student>) -> DiffUtil.Callback = {oldList, newList ->
        StudentDiffUtil(oldList, newList)
    }
}