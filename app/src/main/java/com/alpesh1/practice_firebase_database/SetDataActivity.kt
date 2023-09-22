package com.alpesh1.practice_firebase_database

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SetDataActivity : AppCompatActivity() {

    lateinit var reference: DatabaseReference
    lateinit var recyclerView: RecyclerView
    lateinit var datalist : ArrayList<ModelClass>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_data)

        recyclerView = findViewById(R.id.rcvData1)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        datalist = arrayListOf<ModelClass>()

        getSet()

    }

    private fun getSet() {

        reference = FirebaseDatabase.getInstance().getReference("user")

        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (data in snapshot.children){

                        var list = data.getValue(ModelClass::class.java)
                        datalist.add(list!!)

                    }

                    recyclerView.adapter = SetAdapter(datalist)

                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }
}