package com.chauhan.todo.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.chauhan.todo.R
import com.chauhan.todo.databinding.FragmentEditTaskBinding
import com.chauhan.todo.utils.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditTaskFragment : Fragment(),ConfirmationDialogFragment.ConfirmationDialogListener  {

    private lateinit var binding: FragmentEditTaskBinding
    private lateinit var databaseRef: DatabaseReference
    private val args: EditTaskFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requireActivity().window.navigationBarColor  = ContextCompat.getColor(requireContext(), R.color.white)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val task = args.task
        val taskKey = args.taskKey

        databaseRef = FirebaseDatabase.getInstance().getReference("Task").child(FirebaseAuth.getInstance().currentUser?.uid.toString())

        binding.editTitle.setText(task.title)
        binding.editDescription.setText(task.description)

        binding.editNoteBtn.setOnClickListener {
            val updatedTitle = binding.editTitle.text.toString()
            val updatedDescription = binding.editDescription.text.toString()

            val updatedTask = Task(updatedTitle, updatedDescription)
            databaseRef.child(taskKey).setValue(updatedTask).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "Task Updated Successfully", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                } else {
                    Toast.makeText(context, "Failed to Update Task", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnDelete.setOnClickListener {
            showConfirmationDialog()
        }
    }

    private fun showConfirmationDialog() {
        val dialogFragment = ConfirmationDialogFragment(this)
        dialogFragment.show(childFragmentManager, "ConfirmationDialog")
    }

    override fun onConfirmDelete(taskKey: String) {
        val taskKey = args.taskKey
        databaseRef.child(taskKey).removeValue().addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "Task Deleted Successfully", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            } else {
                Toast.makeText(context, "Failed to Delete Task", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

