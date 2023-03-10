package com.example.realtime_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    var count: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button_Save = findViewById<Button>(R.id.button_save)
        val button_read = findViewById<Button>(R.id.button_read)
        val persona_name = findViewById<TextView>(R.id.personName)
        val persona_age = findViewById<TextView>(R.id.personAge)
        val persona_id = findViewById<TextView>(R.id.personID)
        val text_data = findViewById<TextView>(R.id.textViewData)

        val database = Firebase.database
        val myRef = database.getReference()

        button_Save.setOnClickListener {
            var name = persona_name.text.toString()
            var age = persona_age.text.toString()
            var id = persona_id.text.toString()
            val person = hashMapOf(
                "name" to name,
                "age" to age,
                "id" to id
            )
            myRef.child("person").child("$count").setValue(person)
            count++
            Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()

        }
        button_read.setOnClickListener {
            // Read from the database
            myRef.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val value = snapshot.getValue()
                    text_data.text = value.toString()
                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()


                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, "Failler", Toast.LENGTH_LONG).show()

                }

            })

        }

    }
}