package com.n.letschat


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class SignUp : AppCompatActivity() {

    private  lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnSignUp: Button
    private  lateinit var mDBRef: DatabaseReference
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        mDBRef = Firebase.database("https://letschat-dc249-default-rtdb.asia-southeast1.firebasedatabase.app/").reference

        edtName = findViewById(R.id.edt_name)
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnSignUp = findViewById(R.id.btn_signup)

        btnSignUp.setOnClickListener {
            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            signUp(name, email, password)

            }
        }
         private fun signUp(name: String, email: String, password: String){

             mAuth.createUserWithEmailAndPassword(email, password)
                 .addOnCompleteListener(
                     this
                 ) { task ->
                     if (task.isSuccessful) {

                         addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                         val intent = Intent( this@SignUp, MainActivity::class.java)
                         startActivity(intent)
                     } else {
                        Toast.makeText(this@SignUp, "Error occurred, Please check again!", Toast.LENGTH_SHORT).show()
                     }
                 }
         }

    private fun addUserToDatabase(name: String, email: String, uid:String){
        val user = User(name, email,)
        mDBRef.child("user").child(uid).setValue(user)
        mDBRef.child("user").child(uid).child("username").setValue(name)
    }

}

