package com.example.firebasewithkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebasewithkotlin.Extensions.log
import com.example.firebasewithkotlin.Extensions.showToast
import com.example.firebasewithkotlin.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_firebase_real_time.*

class FirebaseRealTimeActivity : AppCompatActivity(R.layout.activity_firebase_real_time) {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var databaseReference: DatabaseReference
    private var TAG = "mytag"
    lateinit var valueEventListener: ValueEventListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        databaseReference = FirebaseDatabase.getInstance().getReference().child("users")
        valueEventListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                for (userSnapShot in p0.children) {
                    var user: User? =userSnapShot.getValue(User::class.java)
                    log(user?.name+" "+user?.number)
                }
            }
        }

        databaseReference.addValueEventListener(valueEventListener)
        button_upload.setOnClickListener {
            var name = editText_name.text.toString()
            var number = editText_number.text.toString()
            uploadData(name, number)
        }
    }

    private fun uploadData(name: String, number: String) {
        val key = databaseReference.push().key
        val user=User(name,number)


        key?.let {
            databaseReference.child(key).setValue(user)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showToast("Uploaded")
                    } else {
                        log(task.exception?.message)
                    }
                }
        }
    }

    override fun onStop() {
        super.onStop()
        databaseReference.removeEventListener(valueEventListener)
    }

}
