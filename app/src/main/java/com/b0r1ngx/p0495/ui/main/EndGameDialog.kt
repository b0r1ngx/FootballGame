package com.b0r1ngx.p0495.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.b0r1ngx.p0495.R

class EndGameDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        dialog?.findViewById<AppCompatButton>(R.id.ok)?.setOnClickListener {
            findNavController().navigateUp()
            findNavController().navigateUp()
        }
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater
            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.end_game_dialog, null))
                .setOnDismissListener {
                    findNavController().navigateUp()
                    findNavController().navigateUp()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}