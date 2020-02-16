package com.example.firebasewithkotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthProvider
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    var TAG="mytag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()



        signInBtn.setOnClickListener {
            var email = email.text.toString();
            var password = password.text.toString()
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        showToast("Successfully login")
//                        statusTextView.text = "LoginIn"
//                        var firebaseUser: FirebaseUser? = firebaseAuth.currentUser
//                        firebaseUser.let {
//                            var email = firebaseUser?.email
//                            statusTextView.append("\n" + email)
//                        }
                    } else {
                        showToast(task.exception?.message.toString())
                    }
                }

        }


        logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            statusTextView.text = "Successfully logout"
        }
        button3.setOnClickListener {
           // var intent: Intent = Intent(this@MainActivity, Main2Activity::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        FirebaseAuth.getInstance().addAuthStateListener {
            val user=firebaseAuth.currentUser
            if(user!=null){
              //  var intent=Intent(this@MainActivity,Main2Activity::class.java)
                startActivity(intent)

                Log.d(TAG,FirebaseAuth.getInstance().currentUser?.uid);
            }
            else{
                Log.d(TAG,"Not signIn");
            }
        }
    }


}
