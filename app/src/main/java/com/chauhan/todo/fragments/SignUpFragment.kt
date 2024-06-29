package com.chauhan.todo.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.chauhan.todo.R
import com.chauhan.todo.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth:FirebaseAuth
    private lateinit var userName:String
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentSignUpBinding.inflate(inflater,container,false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.splash_screen_status_color)
        }
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requireActivity().window.navigationBarColor  = ContextCompat.getColor(requireContext(), R.color.white)
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)

        binding.dontHave.setOnClickListener {
            navController.navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding.signUpButton.setOnClickListener {

            binding.progressBar2.visibility=View.VISIBLE

            userName=binding.userName.text.toString()
            email=binding.emailUp.text.toString().trim()
            password=binding.passUp.text.toString().trim()

            if(userName.isBlank()||password.isBlank()||email.isBlank()){
                binding.progressBar2.visibility=View.INVISIBLE
                Toast.makeText(context,"Please Fill All The Details",Toast.LENGTH_SHORT).show()
            }else{
                createAccount(email,password)
                binding.progressBar2.visibility=View.INVISIBLE
            }
        }



    }



    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                task ->
            if(task.isSuccessful){
                Toast.makeText(requireContext(),"Account Created Successfully",Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.action_signUpFragment_to_homeFragment)
            }else{
                Toast.makeText(requireContext(),"Account Not Created",Toast.LENGTH_SHORT).show()
                Log.d("Account","createAccount: Failure",task.exception)
            }
        }
    }


    private fun init(view: View) {
       navController=Navigation.findNavController(view)
        auth= FirebaseAuth.getInstance()
    }

}
