package com.example.marvel.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.transition.Slide
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marvel.R
import com.example.marvel.databinding.ActivityRegisterBinding
import com.example.marvel.utils.Validation
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.*


class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    private val auth by lazy {
        Firebase.auth
    }
    private val db by lazy {
        Firebase.firestore
    }
    private val storage by lazy {
        Firebase.storage
    }
    private val mail by lazy {
        binding.edtEmailRegister
    }
    private val password by lazy {
        binding.edtPasswordRegister
    }
    private val mname by lazy {
        binding.edtNameRegister
    }
    private val mmail by lazy {
        binding.edtEmailRegister
    }
    private val mpassword by lazy {
        binding.edtPasswordRegister
    }
    private var selectedPhotoUri: Uri? = null


    @SuppressLint("RtlHardcoded")
    override fun onCreate(savedInstanceState: Bundle?) {
        with(window) {

            enterTransition = Slide().setDuration(1000)
            exitTransition =Slide().setDuration(1000)
        }
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        binding.imgBack.setOnClickListener {
            selectFoto()
        }

        binding.btnRegister.setOnClickListener {
            val email = mmail.editableText.toString()
            val password = mpassword.editableText.toString()

            if (verifyInput()) {


                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Usuário criado com sucesso!!",
                                Toast.LENGTH_SHORT
                            ).show()
                            saveUserFirebase()
                            loadComics()
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Erro ao Registrar usuário!!", Toast.LENGTH_SHORT)
                            .show()

                    }

            }
            return@setOnClickListener


        }


    }

    private fun loadComics() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun verifyInput(): Boolean {
        val tilEmail = binding.tilEmailRegister
        val tilPassword = binding.tilPasswordRegister

        Validation(this).apply {
            return isEmailValid(mail, tilEmail)
                    && isEditTextFilled(password, tilPassword, getString(R.string.password))

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {

             selectedPhotoUri = data?.data
            try {
                selectedPhotoUri?.let { uri->
                    if (Build.VERSION.SDK_INT < 28) {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            this.contentResolver,
                            selectedPhotoUri
                        )
                        binding.imgPhoto.setImageBitmap(bitmap)
                    } else {
                        val source =
                            ImageDecoder.createSource(this.contentResolver, uri)
                        val bitmap = ImageDecoder.decodeBitmap(source)
                        binding.imgPhoto.setImageBitmap(bitmap)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun selectFoto() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 0)
    }


    private fun saveUserFirebase() {

        val user = hashMapOf(
            "name" to mname.text.toString(),
            "email" to mmail.text.toString(),
            "password" to mpassword.text.toString()
        )


        db.collection("users")
            .document(auth.currentUser?.uid ?: "")
            .set(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Usuário salvo com sucesso!", Toast.LENGTH_SHORT).show()

                val storageRef = storage.reference
                val uploadTask = selectedPhotoUri?.let { selected ->
                    storageRef.child("images/${System.currentTimeMillis()}.png").putFile(selected)

                }
                uploadTask?.addOnSuccessListener {
                    Toast.makeText(this, "Imagem salva!", Toast.LENGTH_SHORT).show()
                }


            }
            .addOnFailureListener {
                Toast.makeText(this, "Erro ao salvar usuário!", Toast.LENGTH_SHORT).show()

            }
    }

}
