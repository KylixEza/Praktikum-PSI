package com.kylix.praktikum4

import com.kylix.praktikum4.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    
    override fun determineScreenOrientation(): ScreenOrientation = ScreenOrientation.PORTRAIT
    
    override fun ActivityMainBinding.binder() {
        
        val intentToCalculationActivity = { position: Int ->
            val extraValue = when(position) {
                1 -> Shape.RECTANGULAR
                2 -> Shape.TRIANGLE
                else -> Shape.PARALLELOGRAM
            }
            android.content.Intent(this@MainActivity, CalculationActivity::class.java).apply {
                putExtra(CalculationActivity.EXTRA_SHAPE, extraValue.key)
            }
        }
        
        btnRectangular.setOnClickListener {
            val intent = intentToCalculationActivity.invoke(1)
            startActivity(intent)
        }
        
        btnTriangle.setOnClickListener {
            val intent = intentToCalculationActivity.invoke(2)
            startActivity(intent)
        }
        
        btnParallelogram.setOnClickListener {
            val intent = intentToCalculationActivity.invoke(3)
            startActivity(intent)
        }
    }
}