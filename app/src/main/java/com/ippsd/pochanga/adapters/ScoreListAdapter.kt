package com.ippsd.pochanga.adapters

import android.app.Activity
import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.ippsd.pochanga.R
import kotlinx.android.synthetic.main.list_scores.view.*


class ScoreListAdapter(
    private val context: Activity,
    private val users: ArrayList<String>,
    private var allScores: ArrayList<ArrayList<Int>>)
    : ArrayAdapter<String>(context, R.layout.list_scores, users) {

    var views: ArrayList<View> = ArrayList(users.size)
    var viewholders: ArrayList<MViewHolder> = ArrayList(users.size)

    init {
        for (i in 0 until users.size) {
            val inflater = context.layoutInflater
            val rowView = inflater.inflate(R.layout.list_scores, null, true)

            val userText = rowView.findViewById(R.id.score_user) as TextView
            val scoreText = rowView.findViewById(R.id.score_score) as TextView

            userText.text = users[i]
            scoreText.text = 0.toString()

            views.add(rowView)
            viewholders.add(MViewHolder(rowView, this))
        }
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        return views[position]
    }

    fun getView(position: Int, view: MViewHolder?, parent: ViewGroup): MViewHolder {
        return viewholders[position]
    }
}

class MViewHolder (view: View, par: ScoreListAdapter) : RecyclerView.ViewHolder(view), View.OnClickListener {
    // Holds the TextView that will add each animal to
    val user: TextView = view.score_user
    val score = view.score_score
    val parent = par
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