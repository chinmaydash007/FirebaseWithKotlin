package com.example.firebasewithkotlin.model


data class User(var name: String?="", var number: String="",var userId:String?=null){
    override fun equals(other: Any?): Boolean {
        if(other is User){
            var user=other
            return this.userId.equals(user.userId)
        }
        return false
    }
}