package com.example.fbauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fbauth.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignUpBinding
    private lateinit var firebaseauth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseauth=FirebaseAuth.getInstance()
        binding.btnssin.setOnClickListener{
            val intent= Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }
        binding.btnsup.setOnClickListener{
            val email=binding.etemail2.text.toString()
            val pw=binding.etpw.text.toString()
            val cpw=binding.etcpw.text.toString()
            if(email.isNotEmpty()&&pw.isNotEmpty()&&cpw.isNotEmpty()){
                if(pw==cpw){
                    firebaseauth.createUserWithEmailAndPassword(email,pw).addOnCompleteListener{
                        if(it.isSuccessful){ //we navigate to sigin
                            val intent= Intent(this,SignInActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else{
                    Toast.makeText(this,"BOTH PASSWORDS NOT MATCHING",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"PLEASE FILL ALL FIELDS AND SIGNUP",Toast.LENGTH_SHORT).show()
            }
        }
    }
}