package com.example.fbauth

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.Toast
import com.example.fbauth.databinding.ActivityMainBinding
import com.example.fbauth.databinding.ActivitySignUpBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
   /*companion object{
        val IMG_REQ_CODE=100
    }*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnopgl.setOnClickListener{
            binding.cv.visibility=VISIBLE
            binding.btnall.setOnClickListener{
                binding.cv.visibility=INVISIBLE
                uploadImage(binding.imggal)
            }
            binding.btnden.setOnClickListener{
                binding.cv.visibility=INVISIBLE
                Toast.makeText(this,"ACCESS GOT DENIED!",Toast.LENGTH_SHORT).show()
            }

        }
        binding.btnbck.setOnClickListener{
            val intent= Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }
    }

    private fun uploadImage(imggal: ImageView) {
    val intent=Intent()
        intent.action=Intent.ACTION_GET_CONTENT
        intent.type="image/*"
        startActivityForResult(intent,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1){
            binding.imggal.setImageURI(data?.data)
        }
    }


}