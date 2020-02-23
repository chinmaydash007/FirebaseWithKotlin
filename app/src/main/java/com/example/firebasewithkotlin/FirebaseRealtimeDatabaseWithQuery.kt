package com.example.firebasewithkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasewithkotlin.Adapters.UserAdapter
import com.example.firebasewithkotlin.Adapters.UserAdpterForQuery
import com.example.firebasewithkotlin.Extensions.log
import com.example.firebasewithkotlin.Extensions.showToast
import com.example.firebasewithkotlin.model.User
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_firebase_realtime_database_with_query.*
import kotlinx.android.synthetic.main.single_cardview.*

class FirebaseRealtimeDatabaseWithQuery :
    AppCompatActivity(R.layout.activity_firebase_realtime_database_with_query) {

    lateinit var databaseReference: DatabaseReference

    lateinit var layoutManager: LinearLayoutManager
    lateinit var valueEventListener: ValueEventListener
    lateinit var userList: ArrayList<User>
    lateinit var userAdapter: UserAdpterForQuery
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userList = ArrayList()
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users")
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView2.layoutManager = layoutManager

        userAdapter = UserAdpterForQuery(userList, this)
        recyclerView2.adapter = userAdapter


        button_search.setOnClickListener {

            var text:String?=editText_search.text.toString()
            log(text)
            var query: Query = databaseReference.orderByChild("name").equalTo(text)
            query.addValueEventListener(valueEventListener)

        }


        valueEventListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                for (snapShot in p0.children) {
                    var user: User? = snapShot.getValue(User::class.java)
                    user?.let {
                        userList.add(user)
                    }
                }
                userAdapter.notifyDataSetChanged()
            }

        }


    }


    override fun onStop() {
        super.onStop()
        databaseReference.removeEventListener(valueEventListener)
    }
}
