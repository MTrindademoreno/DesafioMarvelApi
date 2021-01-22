package com.example.marvel.view

import android.app.ActivityOptions
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.transition.Slide
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.marvel.R
import com.example.marvel.databinding.ActivityLoginBinding
import com.example.marvel.utils.Constants.Login.CB_MODE
import com.example.marvel.utils.Constants.Login.EMAIL
import com.example.marvel.utils.Constants.Login.PASSWORD
import com.example.marvel.utils.Validation
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    lateinit var binding: ActivityLoginBinding
    private val auth by lazy {
        Firebase.auth
    }

    private val mail by lazy {
        binding.edtEmail
    }
    private val password by lazy {
        binding.edtPassword
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(window) {

            enterTransition = Slide()
            exitTransition = Slide().setStartDelay(1000)
            reenterTransition = Slide()

        }
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val currentUser = auth.currentUser
        sharedPreferences = getSharedPreferences("Teste_shared", MODE_PRIVATE)
        sharedData()

        binding.tvCreateAccount.setOnClickListener {

            val intent = Intent(this, RegisterActivity::class.java)

            startActivity(
                intent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
            )
        }


        binding.btnLogin.setOnClickListener {
                if (verifyInput()) {
                    auth.signInWithEmailAndPassword(mail.text.toString(), password.text.toString())
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                loadComics()
                            }
                        }
                        .addOnFailureListener {

                            Toast.makeText(this, "Crie uma conta!", Toast.LENGTH_SHORT).show()
                        }
                }
                return@setOnClickListener

        }

        binding.cbRegister.setOnCheckedChangeListener { _, isChecked ->
            if (verifyInput()) {
                sharedPreferences.edit {
                    putString(EMAIL, binding.edtEmail.editableText.toString())
                    putString(PASSWORD, binding.edtPassword.editableText.toString())
                    putBoolean(CB_MODE, isChecked)
                }
            } else Toast.makeText(this, "Preencha os dados antes de selecionar", Toast.LENGTH_SHORT)
                .show()
            return@setOnCheckedChangeListener

        }
    }

    override fun onRestart() {
        super.onRestart()
        if (!binding.cbRegister.isChecked) {
            mail.text?.clear()
            password.text?.clear()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        auth.signOut()
    }

    private fun loadComics() {
        if (verifyInput()) {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun verifyInput(): Boolean {
        val tilEmail = binding.tilEmailLogin
        val tilPassword = binding.tilPasswordLogin

        Validation(this).apply {
            return isEmailValid(mail, tilEmail)
                    && isEditTextFilled(password, tilPassword, getString(R.string.password))

        }
    }

    private fun sharedData() {
        mail.text?.clear()
        val cbMode = sharedPreferences.getBoolean(CB_MODE, true)
        binding.cbRegister.isChecked = cbMode
        if (binding.cbRegister.isChecked) {
            val sEmail = sharedPreferences.getString(EMAIL, "")
            val sPassword = sharedPreferences.getString(PASSWORD, "")
            mail.setText(sEmail)
            password.setText(sPassword)
            binding.tvCreateAccount.visibility = View.GONE


        }

    }


}

