package com.example.firebasewithkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebasewithkotlin.Adapters.UserAdapter
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
    lateinit var childEventListener: ChildEventListener
    lateinit var userAdapter: UserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        var userList = ArrayList<User>()

        userAdapter = UserAdapter(userList, this)
        var linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = userAdapter


        databaseReference = FirebaseDatabase.getInstance().getReference().child("users")
        childEventListener = object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                var user = p0.getValue(User::class.java)
                user?.userId = p0.key
                log(user?.name + " " + user?.number + " " + user?.userId)
                user?.let { userList.add(it) }
                userAdapter.notifyDataSetChanged()
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                var user = p0.getValue(User::class.java)
                user?.userId = p0.key
                userList.remove(user)
                userAdapter.notifyDataSetChanged()
            }
        }
        databaseReference.addChildEventListener(childEventListener)


        button_upload.setOnClickListener {
            var name = editText_name.text.toString()
            var number = editText_number.text.toString()
            uploadData(name, number)
        }
    }

    private fun uploadData(name: String, number: String) {
        val key = databaseReference.push().key
        val user = User(name, number)




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
        databaseReference.removeEventListener(childEventListener)
    }

}
