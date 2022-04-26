package com.b0r1ngx.p0495.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.b0r1ngx.p0495.R
import com.b0r1ngx.p0495.MainActivity.Companion.indexHistory
import com.b0r1ngx.p0495.MainActivity.Companion.score
import com.b0r1ngx.p0495.databinding.MainFragmentBinding


class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        initScore()
        initButtonsLogic()

        return binding.root
    }

    private fun initScore() {
        score = activity?.getPreferences(Context.MODE_PRIVATE)
            ?.getInt(getString(R.string.preference_score), 20) ?: 20
        binding.score.text = score.toString()

        indexHistory = activity?.getPreferences(Context.MODE_PRIVATE)
            ?.getInt(getString(R.string.index_history), 0) ?: 0
    }

    private fun initButtonsLogic() {
        binding.start.setOnClickListener {
            findNavController().navigate(R.id.gameFragment)
        }

        binding.results.setOnClickListener {
            findNavController().navigate(R.id.resultsFragment)
        }

        binding.politics.setOnClickListener {
            findNavController().navigate(R.id.politicsFragment)
        }

        binding.exit.setOnClickListener {
            Log.d("Test", "Exit press")
            this.activity?.finish()
        }
    }
}