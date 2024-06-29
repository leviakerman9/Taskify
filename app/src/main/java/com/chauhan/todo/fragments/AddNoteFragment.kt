package com.chauhan.todo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.chauhan.todo.R
import com.chauhan.todo.databinding.FragmentAddNoteBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class AddNoteFragment : Fragment() {
    private lateinit var binding: FragmentAddNoteBinding
    private lateinit var listener:DialogNextBtnClickListener


    fun setListener(listener: DialogNextBtnClickListener){
        this.listener=listener
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding= FragmentAddNoteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val imeVisible = insets.isVisible(WindowInsetsCompat.Type.ime())
            if (imeVisible) {
                // Adjust the view to handle keyboard visibility if necessary
                // For example, you might want to adjust padding or margin
            }
            WindowInsetsCompat.CONSUMED
        }

        addTask()
    }

    private fun addTask() {
        binding.addNote.setOnClickListener {
            val title=binding.editNoteTitle.text.toString()
            val description=binding.editNoteDesc.text.toString()

            if(title.isNotEmpty() && description.isNotEmpty()){
                listener.saveTask(title,description, binding.editNoteTitle, binding.editNoteDesc)
            }else{
                Toast.makeText(context,"Please Add Note",Toast.LENGTH_SHORT).show()
            }
        }
    }

    interface  DialogNextBtnClickListener{

        fun saveTask(title:String , description:String, editNoteTitle:EditText, editNoteDesc:EditText)

    }



}