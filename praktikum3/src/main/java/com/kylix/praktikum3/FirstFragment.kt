package com.kylix.praktikum3

import android.view.ViewGroup
import androidx.fragment.app.commit
import com.kylix.praktikum3.databinding.FragmentFirstBinding

class FirstFragment : BaseFragment<FragmentFirstBinding>() {
    
    override fun inflateViewBinding(container: ViewGroup?): FragmentFirstBinding =
        FragmentFirstBinding.inflate(layoutInflater, container, false)
    
    override fun FragmentFirstBinding.binder() {
        btnNext.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container_first_second,  SecondFragment())
                addToBackStack(null)
            }
        }
    }
    
    override fun determineScreenOrientation(): ScreenOrientation = ScreenOrientation.PORTRAIT
}