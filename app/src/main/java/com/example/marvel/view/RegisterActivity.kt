package com.example.marvel.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvel.databinding.ActivityRegisterBinding
import com.example.marvel.model.User
import com.example.marvel.viewModel.LoginViewModel

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var viewModel: LoginViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel=ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.btnRegister.setOnClickListener {
            val name = binding.edtNameRegister.editableText.toString()
            val email = binding.edtEmailRegister.editableText.toString()
            val password = binding.edtNameRegister.editableText.toString()
            val user = User(name, email, password)
            viewModel.saveUser(user)


        }


    }
}