package com.example.fbauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fbauth.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        firebaseAuth = FirebaseAuth.getInstance()
        setContentView(binding.root)
        binding.btnssup.setOnClickListener{
            val intent=Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.btnsin.setOnClickListener {
            val email = binding.etemail2.text.toString()
            val pw = binding.etpw2.text.toString()
            if (email.isNotEmpty() && pw.isNotEmpty()) {
             firebaseAuth.signInWithEmailAndPassword(email,pw).addOnCompleteListener{
                 if(it.isSuccessful){
                     val intent=Intent(this,MainActivity::class.java)
                     startActivity(intent)
                 }
                 else{
                     Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                 }
             }
            } else {
                Toast.makeText(this, "PLEASE FILL ALL FIELDS AND SIGNIN", Toast.LENGTH_SHORT).show()
            }
        }

    }
}