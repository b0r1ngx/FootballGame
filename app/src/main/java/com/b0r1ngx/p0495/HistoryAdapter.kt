package com.b0r1ngx.p0495

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.b0r1ngx.p0495.data.History

class HistoryAdapter(private val history: ArrayList<History>) :
    RecyclerView.Adapter<HistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder =
        HistoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.result_item, parent, false)
        )

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) =
        holder.bind(history[position])

    override fun getItemCount(): Int = history.size
}

class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val index  = view.findViewById<TextView>(R.id.index)
    private val user  = view.findViewById<TextView>(R.id.user)
    private val score  = view.findViewById<TextView>(R.id.score)

    fun bind(h: History) {
        index.text = h.index.toString()
        user.text = h.user
        score.text = h.score.toString()
    }
}