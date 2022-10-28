package com.kylix.praktikum4

import android.annotation.SuppressLint
import com.jakewharton.rxbinding2.widget.RxTextView
import com.kylix.praktikum4.databinding.ActivityCalculationBinding
import io.reactivex.Observable

class CalculationValidator: ConstraintValidator<ActivityCalculationBinding> {
    
    @SuppressLint("CheckResult")
    override fun ActivityCalculationBinding.validate() {
        val emptyFirstAttribute = RxTextView.textChanges(etFirstAttr)
            .map { it.isEmpty() }
        
        emptyFirstAttribute.subscribe {
            if(it) etFirstAttr.error = "Tidak boleh kosong"
        }
        
        val emptySecondAttribute = RxTextView.textChanges(etSecondAttr)
            .map { it.isEmpty() }
        
        emptySecondAttribute.subscribe {
            if(it) etSecondAttr.error = "Tidak boleh kosong"
        }
        
        Observable.combineLatest(emptyFirstAttribute, emptySecondAttribute) { first, second ->
            !first && !second
        }.subscribe {
            btnCalculate.isEnabled = it
        }
    }
}