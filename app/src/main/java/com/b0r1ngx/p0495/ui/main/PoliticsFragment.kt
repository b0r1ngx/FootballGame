package com.b0r1ngx.p0495.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.b0r1ngx.p0495.databinding.PoliticsFragmentBinding

class PoliticsFragment: Fragment() {
    private lateinit var binding: PoliticsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PoliticsFragmentBinding.inflate(inflater, container, false)
        initButtonsLogic()
        return binding.root
    }

    private fun initButtonsLogic() {
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}