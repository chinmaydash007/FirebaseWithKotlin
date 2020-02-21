package com.example.firebasewithkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_activtity.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseAuthStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuthStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser == null) {
                var intent = Intent(this@MainActivity, LoginActivtity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK and Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                finish()
            }

        }
        button_firebaseRealTime.setOnClickListener {
            var intent = Intent(this, FirebaseRealTimeActivity::class.java)
            startActivity(intent)
        }
        button_firebaseFireStore.setOnClickListener {
            var intent = Intent(this, FirebaseFirestoreActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser == null) {
            var intent = Intent(this@MainActivity, LoginActivtity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK and Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()

        }
        FirebaseAuth.getInstance().addAuthStateListener(firebaseAuthStateListener)

    }

    override fun onStop() {
        super.onStop()
        FirebaseAuth.getInstance().removeAuthStateListener(firebaseAuthStateListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                firebaseAuth.signOut()
            }
        }
        return true
    }

}
