package com.example.firebasewithkotlin.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.firebasewithkotlin.Extensions.log
import com.example.firebasewithkotlin.Extensions.showToast
import com.example.firebasewithkotlin.R
import com.example.firebasewithkotlin.model.User
import com.example.firebasewithkotlin.network.removeUser
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.single_cardview.view.*

class UserAdapter(var list: List<User>, var context: Context) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var view: View =
            LayoutInflater.from(context).inflate(R.layout.single_cardview, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.textView.text = list[position].name
        holder.textView2.text = list[position].number
        holder.cardView.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                var task: Task<Void> = removeUser(list[position].userId.toString())
                task.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        context.showToast("Deleted!!")
                    } else {
                        log(task.exception?.message)
                    }
                }
                return true
            }

        })

    }

    inner class UserViewHolder(itemView: View) : ViewHolder(itemView) {
        var cardView = itemView.cardview
        var textView = itemView.textView_name
        var textView2 = itemView.textView_number


    }
}