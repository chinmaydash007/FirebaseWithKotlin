package com.example.firebasewithkotlin.network

import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase

fun removeUser(userId: String): Task<Void> {
    var task: Task<Void> =
        FirebaseDatabase.getInstance().getReference().child("users").child(userId).setValue(null)
    return task
}