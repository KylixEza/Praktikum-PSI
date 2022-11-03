package com.kylix.praktikum5.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity() {

    private lateinit var _binding: VB
    val binding get() = _binding

    abstract fun inflateViewBinding(): VB
    abstract fun determineScreenOrientation(): ScreenOrientation?
    abstract fun VB.binder()

    open fun constraintValidator(): ConstraintValidator<VB>? { return null }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflateViewBinding()
        setContentView(binding.root)

        val screenOrientation = determineScreenOrientation()

        requestedOrientation = if(screenOrientation != null) {
            if (screenOrientation == ScreenOrientation.PORTRAIT)
                android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            else
                android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            android.content.pm.ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
        }

        binding.apply {
            binder()
            constraintValidator()?.apply { validate() }
        }

    }

    fun View.show() = run { visibility = View.VISIBLE }
    fun View.hide() = run { visibility = View.INVISIBLE }
    fun View.dispose() = run { visibility = View.GONE }
    
}

interface ConstraintValidator<VB: ViewBinding> {
    fun VB.validate()
}

enum class ScreenOrientation {
    PORTRAIT,
    LANDSCAPE,
}