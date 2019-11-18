package com.ippsd.pochanga.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ippsd.pochanga.R
import com.ippsd.pochanga.adapters.ScoreListAdapter
import kotlinx.android.synthetic.main.activity_input_score.*
import kotlinx.android.synthetic.main.content_input_score.*


class InputScore : AppCompatActivity() {

    private var users: ArrayList<String> = ArrayList()
    private var scores: ArrayList<ArrayList<Int>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_score)
        setSupportActionBar(toolbar)

        @Suppress("UNCHECKED_CAST")
        val localUsers: ArrayList<String> = intent.extras!!.get("groups") as ArrayList<String>
        addPlayers(localUsers)

        var initialScores: ArrayList<Int> = ArrayList(localUsers.size)
        for (i in 0 until localUsers.size) { initialScores.add(0) }

        println(localUsers.size)
        var adapter = ScoreListAdapter(this, localUsers, scores)
        listview.adapter = adapter


        fab_add.setOnClickListener { view ->
            var local_scores: ArrayList<Int> = ArrayList()
            for (i in 0 until users.size) {
                var score = adapter.viewholders[i].getScore()
                local_scores.add(score)
                adapter.viewholders[i].score!!.text = 0.toString()
            }
            scores.add(local_scores)
        }

        fab_view.setOnClickListener { view ->
            val intent = Intent(this, DisplayActivity::class.java)
            intent.putExtra("users", users)
            intent.putExtra("scores", scores)
            println(scores)
            startActivity(intent)
        }
    }

    private fun addPlayers(loc: ArrayList<String>) {
        for (user in loc){
            users.add(user)
        }
    }

}
