package com.chauhan.todo.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chauhan.todo.R
import com.chauhan.todo.databinding.FragmentHomeBinding
import com.chauhan.todo.utils.Task
import com.chauhan.todo.utils.TaskAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment(), AddNoteFragment.DialogNextBtnClickListener, TaskAdapter.TaskInterface {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference
    private lateinit var navController: NavController
    private lateinit var popUpFragment: AddNoteFragment
    private lateinit var taskList: MutableList<Task>
    private lateinit var taskKeys: MutableList<String>
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.button_color)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                requireActivity().window.navigationBarColor  = ContextCompat.getColor(requireContext(), R.color.white)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        getDataFromFirebase()
        addNotes()
    }


    private fun getDataFromFirebase() {
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                taskList.clear()
                taskKeys.clear()
                for (taskSnapshot in snapshot.children) {
                    val task = taskSnapshot.getValue(Task::class.java)
                    val taskKey = taskSnapshot.key

                    if (task != null && taskKey != null) {
                        taskList.add(task)
                        taskKeys.add(taskKey)
                    }
                }
                taskAdapter.notifyDataSetChanged()
                updateEmptyState()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Failed to load tasks", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateEmptyState() {
        binding.emptyStateImage.isVisible = taskList.isEmpty()
    }


    private fun addNotes() {
        binding.addNoteButton.setOnClickListener {
            popUpFragment = AddNoteFragment()
            popUpFragment.setListener(this)
            val transaction = childFragmentManager.beginTransaction()
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            transaction.add(R.id.nav_host_fragment_container, popUpFragment).addToBackStack(null).commit()
        }
    }

    private fun init(view: View) {
        navController = Navigation.findNavController(view)
        auth = Firebase.auth
        databaseRef = Firebase.database.reference.child("Task").child(auth.currentUser?.uid.toString())

        binding.recylerView.setHasFixedSize(true)
        taskList = mutableListOf()
        taskKeys = mutableListOf()
        binding.recylerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        taskAdapter = TaskAdapter(taskList, taskKeys)
        taskAdapter.setListener(this)
        binding.recylerView.adapter = taskAdapter
    }

    override fun saveTask(
        title: String,
        description: String,
        editNoteTitle: EditText,
        editNoteDesc: EditText
    ) {
        val task = Task(title, description)
        databaseRef.push().setValue(task).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "Task Added Successfully !!..", Toast.LENGTH_SHORT).show()
                editNoteTitle.text = null
                editNoteDesc.text = null
            } else {
                Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
            childFragmentManager.popBackStack()
        }
    }

    override fun onItemClick(task: Task, taskKey: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToEditTaskFragment(task, taskKey)
        navController.navigate(action)
    }



}

