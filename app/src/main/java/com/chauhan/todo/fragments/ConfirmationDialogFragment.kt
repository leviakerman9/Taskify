// ConfirmationDialogFragment.kt
package com.chauhan.todo.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.chauhan.todo.databinding.FragmentConfirmationDialogBinding

class ConfirmationDialogFragment(private val listener: ConfirmationDialogListener) : DialogFragment() {

    interface ConfirmationDialogListener {
        fun onConfirmDelete(taskKey: String)
    }

    private lateinit var binding: FragmentConfirmationDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConfirmationDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskKey = arguments?.getString("taskKey").orEmpty()

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        binding.deleteButton.setOnClickListener {
            listener.onConfirmDelete(taskKey)
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setCanceledOnTouchOutside(false)
        }
    }
}
