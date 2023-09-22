package com.alpesh1.practice_firebase_database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alpesh1.practice_firebase_database.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {

            var name = binding.edtName.text.toString()
            var surname = binding.edtSurname.text.toString()

            reference = FirebaseDatabase.getInstance().reference

            var key = reference.root.push().key

            var model = ModelClass(key!!,name, surname)

            reference.root.child("user").child(key).setValue(model)

            binding.edtName.setText("")
            binding.edtSurname.setText("")

            var intent = Intent(this,SetDataActivity::class.java)
            startActivity(intent)
        }

        binding.btnShowData.setOnClickListener {

            var intent = Intent(this,SetDataActivity::class.java)
            startActivity(intent)

        }


    }

}