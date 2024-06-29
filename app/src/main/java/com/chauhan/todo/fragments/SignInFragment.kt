package com.chauhan.todo.fragments

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.chauhan.todo.R
import com.chauhan.todo.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var navController: NavController
    private lateinit var auth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSignInBinding.inflate(inflater,container,false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.splash_screen_status_color)
        }
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requireActivity().window.navigationBarColor  = ContextCompat.getColor(requireContext(), R.color.button_color)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth=Firebase.auth
        navController=Navigation.findNavController(view)

        binding.dontHave.setOnClickListener {
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.signInButton.setOnClickListener {
            val email=binding.emailIn.text.toString().trim()
            val pass=binding.passIn.text.toString().trim()

            binding.progressBar1.visibility=View.VISIBLE
            if(email.isNotEmpty() && pass.isNotEmpty()){
                loginUser(email,pass)
                binding.progressBar1.visibility=View.INVISIBLE
                Toast.makeText(context,"Login successfully",Toast.LENGTH_SHORT).show()
            }else{
                binding.progressBar1.visibility=View.INVISIBLE
                Toast.makeText(context,"please fill all details",Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun loginUser(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
            if(it.isSuccessful){
                navController.navigate(R.id.action_signInFragment_to_homeFragment)
            }else{
                Toast.makeText(context,"please create account",Toast.LENGTH_SHORT).show()
            }
        }
    }


}