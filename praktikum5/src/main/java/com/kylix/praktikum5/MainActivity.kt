package com.kylix.praktikum5

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kylix.praktikum5.base.BaseActivity
import com.kylix.praktikum5.base.ScreenOrientation
import com.kylix.praktikum5.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var viewModel: MainViewModel
    private val adapter by lazy { StudentAdapter() }
    
    override fun inflateViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    
    override fun determineScreenOrientation(): ScreenOrientation? = null
    
    override fun ActivityMainBinding.binder() {
        viewModel = ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
        
        rvStudent.apply {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
        
        viewModel.students.observe(this@MainActivity) {
            adapter.submitList(it)
        }
        
        btnSubmit.setOnClickListener {
            if (etId.text.toString().isNotEmpty() && etName.text.toString().isNotEmpty() && etClassroom.text.toString().isNotEmpty()) {
                viewModel.setNewStudent(etId.text.toString(), etName.text.toString(), etClassroom.text.toString())
                etId.setText("")
                etName.setText("")
                etClassroom.setText("")
            } else {
                Toast.makeText(this@MainActivity, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}