package com.b0r1ngx.p0495.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.b0r1ngx.p0495.HistoryAdapter
import com.b0r1ngx.p0495.MainActivity.Companion.globalHistory
import com.b0r1ngx.p0495.databinding.ResultsFragmentBinding

class ResultsFragment: Fragment() {
    private lateinit var binding: ResultsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ResultsFragmentBinding.inflate(inflater, container, false)
        initButtonsLogic()
        getResults()
        return binding.root
    }

    private fun getResults() {
        Log.d("Test", "history size: ${globalHistory.size}")
        if (globalHistory.isNotEmpty()) {
            globalHistory.sortByDescending { it.index }
            Log.d("Test", "globalHistory: $globalHistory")
            val viewManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            val viewAdapter = HistoryAdapter(globalHistory)
            binding.historyRecyclerView.layoutManager = viewManager
            binding.historyRecyclerView.adapter = viewAdapter
        } else {
            // you not played yet!
        }
    }

    private fun initButtonsLogic() {
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}