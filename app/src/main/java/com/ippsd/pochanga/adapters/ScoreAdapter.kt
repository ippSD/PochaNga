package com.ippsd.pochanga.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.ippsd.pochanga.R
import kotlinx.android.synthetic.main.list_scores.view.*


class ScoreAdapter(
    val users: ArrayList<String>,
    val context: Context
) : RecyclerView.Adapter<MyViewHolder>() {

    var scores: ArrayList<Int>? = null

    init {
        scores = ArrayList(users.size)
        for (i in 0..users.size) {
            scores!![i] = 0
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_scores, p0, false)
        )
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.user.text = users[p1]
        p0.score.text = "0"
    }

    override fun getItemCount(): Int {
        return users.size
    }
}

class MyViewHolder (view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
    // Holds the TextView that will add each animal to
    val user: TextView = view.score_user
    val score = view.score_score
    private var buttonAdd: Button = view.score_add
    private var buttonSub: Button = view.score_sub

    init {
        buttonAdd = view.score_add
        buttonSub = view.score_sub

        buttonAdd.setTag(R.integer.btn_plus_view, view)
        buttonSub.setTag(R.integer.btn_minus_view, view)
        score.setTag(R.integer.score, view)

        buttonAdd.setOnClickListener(this)
        buttonSub.setOnClickListener(this)
    }

    fun getScore(): Int {
        return score.text.toString().toInt()
    }

    // onClick Listener for view
    override fun onClick(v: View) {
        print("HELP!")

        if (v.id == buttonAdd.id) {

            val tempview = buttonAdd.getTag(R.integer.btn_plus_view) as View
            val scoreNow = score.text.toString().toInt()
            val scoreNew = scoreNow + 1
            score.text = scoreNew.toString()

        } else if (v.id == buttonSub.id) {
            val scoreNow = score.text.toString().toInt()
            val scoreNew = scoreNow - 1
            score.text = scoreNew.toString()
        }
    }
}