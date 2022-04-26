package com.b0r1ngx.p0495.ui.main

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.callbacks.onDismiss
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.b0r1ngx.p0495.MainActivity.Companion.gameScore
import com.b0r1ngx.p0495.MainActivity.Companion.indexHistory
import com.b0r1ngx.p0495.MainActivity.Companion.score
import com.b0r1ngx.p0495.MainActivity.Companion.globalHistory
import com.b0r1ngx.p0495.R
import com.b0r1ngx.p0495.data.History
import com.b0r1ngx.p0495.databinding.GameFragmentBinding
import kotlin.random.Random


class GameFragment : Fragment() {
    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = GameFragmentBinding.inflate(inflater, container, false)

        initButtonsLogic()
        startGame()

        return binding.root
    }

    private fun startGame() {
        val countries = mutableListOf(
            Pair("Russia", R.drawable.russia),
            Pair("Argentina", R.drawable.argentina),
            Pair("Belgium", R.drawable.belgium),
            Pair("Brazil", R.drawable.brazil),
            Pair("Denmark", R.drawable.denmark),
            Pair("France", R.drawable.france),
            Pair("Italy", R.drawable.italy),
            Pair("Mexico", R.drawable.mexico),
            Pair("Portugal", R.drawable.portugal),
            Pair("Spain", R.drawable.spain),
            Pair("UK", R.drawable.uk)
        )

        val event = listOf(countries.removeAt(Random.nextInt(0, countries.size)),
                            countries.removeAt(Random.nextInt(0, countries.size-1)))

        val result = listOf("w1", "draw", "w2")[Random.nextInt(0, 3)]

        binding.country1.setImageResource(event[0].second)
        binding.country1Text.text = event[0].first

        binding.country2.setImageResource(event[1].second)
        binding.country2Text.text = event[1].first

        val win = R.drawable.win
        val lose = R.drawable.lose

        binding.w1.setOnClickListener {
            if (result == "w1") {
                binding.result.setImageResource(win)
                gameScore += 1
                initResult()
            } else {
                binding.result.setImageResource(lose)
                gameScore -= 1
                initResult()
            }
        }

        binding.draw.setOnClickListener {
            if (result == "draw") {
                binding.result.setImageResource(win)
                gameScore += 1
                initResult()
            } else {
                binding.result.setImageResource(lose)
                gameScore -= 1
                initResult()
            }
        }

        binding.w2.setOnClickListener {
            if (result == "w2") {
                binding.result.setImageResource(win)
                gameScore += 1
                initResult()
            } else {
                binding.result.setImageResource(lose)
                gameScore -= 1
                initResult()
            }
        }
    }

    private fun initResult() {
        binding.w1.isClickable = false
        binding.draw.isClickable = false
        binding.w2.isClickable = false

        binding.result.setOnClickListener {
            val direction = GameFragmentDirections.actionGameFragmentSelf()
            findNavController().navigate(direction)
        }
    }

    private fun initButtonsLogic() {
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.endGame.setOnClickListener {
            indexHistory += 1
            score += gameScore

            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return@setOnClickListener
            with(sharedPref.edit()) {
                putInt(getString(R.string.preference_score), score)
                putInt(getString(R.string.index_history), indexHistory)
                apply()
            }
            globalHistory.add(History(indexHistory, score = gameScore))
            gameScore = 0

            MaterialDialog(context ?: return@setOnClickListener).show {
                customView(R.layout.end_game_dialog, noVerticalPadding = true)
                val window = this.window
                val wlp = window?.attributes
//                wlp?.gravity = Gravity.TOP
//                wlp?.dimAmount = 0.0f
                wlp?.y = -500
                window?.attributes = wlp
//                window?.addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND)
                val view = getCustomView()
                view.findViewById<TextView>(R.id.score).text = score.toString()

                view.findViewById<AppCompatButton>(R.id.ok).setOnClickListener {
                    onBackPressed()
                    findNavController().navigateUp()
                }

                onDismiss {
                    findNavController().navigateUp()
                }
            }
        }
    }
}
