package com.example.marvel.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import com.example.marvel.R
import com.example.marvel.Validation
import com.example.marvel.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("Teste_shared", MODE_PRIVATE)

        sharedData()


        binding.tvCreateAccount.setOnClickListener {

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {

            if(verifyInput()){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }
        binding.cbRegister.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit {
                putString("email", binding.edtEmail.editableText.toString())
                putString("password", binding.edtPassword.editableText.toString())
                putBoolean("cb_mode", isChecked)
            }
        }


    }

    private fun verifyInput(): Boolean {
        val mEmail = binding.edtEmail
        val mPassword = binding.edtPassword
        val tilEmail = binding.tilEmailLogin
        val tilPassword = binding.tilPasswordLogin

        Validation(this).apply {
            return isEmailValid(mEmail, tilEmail)
                    && isEditTextFilled(mPassword, tilPassword, getString(R.string.password))

        }
    }

    private fun sharedData() {
        val cb_mode = sharedPreferences.getBoolean("cb_mode", true)
        val email = sharedPreferences.getString("email", "")
        val password = sharedPreferences.getString("password", "")
        binding.cbRegister.isChecked = cb_mode
        if (binding.cbRegister.isChecked) {

            binding.edtEmail.setText(email)
            binding.edtPassword.setText(password)
        }
    }
}