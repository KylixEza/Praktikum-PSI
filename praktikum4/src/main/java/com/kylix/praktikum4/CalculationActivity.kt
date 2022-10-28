package com.kylix.praktikum4

import android.content.Context
import android.content.SharedPreferences
import com.kylix.praktikum4.databinding.ActivityCalculationBinding

class CalculationActivity : BaseActivity<ActivityCalculationBinding>() {
    
    private lateinit var validator: CalculationValidator
    private lateinit var sharedPref: SharedPreferences
    
    companion object {
        const val EXTRA_SHAPE = "EXTRA_SHAPE"
        const val KEY_FIRST_ATTR = "KEY_FIRST_ATTR"
        const val KEY_SECOND_ATTR = "KEY_SECOND_ATTR"
        const val KEY_RESULT = "KEY_RESULT"
        const val KEY_SHARED = "SHARED_CALCULATION"
    }
    
    override fun inflateViewBinding(): ActivityCalculationBinding = ActivityCalculationBinding.inflate(layoutInflater)
    
    override fun determineScreenOrientation(): ScreenOrientation? = null
    
    override fun ActivityCalculationBinding.binder() {
        val shape = intent.getStringExtra(EXTRA_SHAPE) ?: ""
        sharedPref = getSharedPreferences(KEY_SHARED, Context.MODE_PRIVATE)
        
        val titleShape = when(shape) {
            Shape.RECTANGULAR.key -> "Persegi Panjang"
            Shape.TRIANGLE.key -> "Segitiga"
            Shape.PARALLELOGRAM.key -> "Jajargenjang"
            else -> ""
        }
        tvTitle.text = resources.getString(R.string.calculation_title, titleShape)
        
        validator = CalculationValidator()
        
        when(shape) {
            Shape.RECTANGULAR.key -> {
                tvFirstAttribute.text = "Panjang"
                tvSecondAttribute.text = "Lebar"
            }
            Shape.TRIANGLE.key -> {
                tvFirstAttribute.text = "Alas"
                tvSecondAttribute.text = "Tinggi"
            }
            Shape.PARALLELOGRAM.key -> {
                tvFirstAttribute.text = "Alas"
                tvSecondAttribute.text = "Tinggi"
            }
        }
        
        sharedPref.apply {
            etFirstAttr.setText(getFloat(KEY_FIRST_ATTR, 0.0F).toDouble().toString())
            etSecondAttr.setText(getFloat(KEY_SECOND_ATTR, 0.0F).toDouble().toString())
            tvResult.text = getFloat(KEY_RESULT, 0.0F).toDouble().toString()
        }
        
        btnCalculate.setOnClickListener {
            calculateArea(shape)
        }
    }
    
    override fun constraintValidator(): ConstraintValidator<ActivityCalculationBinding> = validator
    
    private fun calculateArea(shape: String) {
        binding.apply {
            val firstAttribute = etFirstAttr.text.toString().toDouble()
            val secondAttribute = etSecondAttr.text.toString().toDouble()
            
            val area = when(shape) {
                Shape.RECTANGULAR.key -> firstAttribute.times(secondAttribute)
                Shape.TRIANGLE.key -> (firstAttribute.times(secondAttribute)).div(2)
                Shape.PARALLELOGRAM.key -> firstAttribute.times(secondAttribute)
                else -> 0.0
            }
            
            saveSharedPref(
                Pair(KEY_FIRST_ATTR, firstAttribute),
                Pair(KEY_SECOND_ATTR, secondAttribute),
                Pair(KEY_RESULT, area)
            )
            tvResult.text = area.toString()
        }
    }
    
    private fun saveSharedPref(vararg keyToValue: Pair<String, Double>) {
        val editor = sharedPref.edit()
        keyToValue.forEach {
            editor.putFloat(it.first, it.second.toFloat())
        }
        editor.apply()
    }
}