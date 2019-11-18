package com.ippsd.pochanga.users

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ippsd.pochanga.R

class UserAdapter(
    context: Context,
    private val dataSource: ArrayList<User>): BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun delItemId(position: Int) {
        dataSource.removeAt(position)
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.list_item_user, parent, false)
        val userTextView = rowView.findViewById(R.id.textview_user) as TextView
        val user = getItem(position) as User
        userTextView.text = user.getUsername()
        if (user.checked) userTextView.setBackgroundColor(Color.GREEN)

        return rowView
    }

}