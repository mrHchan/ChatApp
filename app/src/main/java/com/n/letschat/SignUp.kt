package com.n.letschat

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class SignUp : AppCompatActivity() {

    private  lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnSignUp: Button

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()

        edtName = findViewById(R.id.edt_name)
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnSignUp = findViewById(R.id.btn_signup)

        btnSignUp.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            signUp(email, password)

            }
        }
         private fun signUp(email: String, password: String){
             mAuth.createUserWithEmailAndPassword(email, password)
                 .addOnCompleteListener(
                     this
                 ) { task ->
                     if (task.isSuccessful) {
                         val intent = Intent( this@SignUp, MainActivity::class.java)
                         startActivity(intent)
                     } else {
                        Toast.makeText(this@SignUp, "Error occurred, Please check again!", Toast.LENGTH_SHORT).show()
                     }
                 }
         }
    }

