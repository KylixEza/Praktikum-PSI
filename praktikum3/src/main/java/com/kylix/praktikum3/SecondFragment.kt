package com.kylix.praktikum3

import android.view.ViewGroup
import com.kylix.praktikum3.databinding.FragmentSecondBinding

class SecondFragment : BaseFragment<FragmentSecondBinding>() {
    override fun inflateViewBinding(container: ViewGroup?): FragmentSecondBinding =
        FragmentSecondBinding.inflate(layoutInflater, container, false)
    
    override fun FragmentSecondBinding.binder() {
    
    }
    
    override fun determineScreenOrientation(): ScreenOrientation = ScreenOrientation.PORTRAIT
    
    override fun onBackPressedBehaviour() {
        activity?.finish()
    }
    
}