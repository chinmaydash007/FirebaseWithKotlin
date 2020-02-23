package com.example.firebasewithkotlin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasewithkotlin.R
import com.example.firebasewithkotlin.model.User
import kotlinx.android.synthetic.main.single_cardview.view.*

class UserAdpterForQuery(var list: List<User>, var context: Context) :
    RecyclerView.Adapter<UserAdpterForQuery.UserViewHolderforQuery>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolderforQuery {
        var view: View =
            LayoutInflater.from(context).inflate(R.layout.single_cardview, parent, false)
        return UserViewHolderforQuery(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: UserViewHolderforQuery, position: Int) {
        holder.textview_name.text = list[position].name
        holder.textview_number.text = list[position].number

    }

    inner class UserViewHolderforQuery(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textview_name = itemView.textView_name
        var textview_number = itemView.textView_number
    }


}