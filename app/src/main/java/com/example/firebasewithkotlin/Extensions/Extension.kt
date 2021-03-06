package com.example.firebasewithkotlin.Extensions

import android.content.Context
import android.util.Log
import android.widget.Toast

fun Context.showToast(str: String) {
    Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
}

public var TAG: String = "mytag"
fun log(msg: String?) {
    Log.d(TAG, msg)
}