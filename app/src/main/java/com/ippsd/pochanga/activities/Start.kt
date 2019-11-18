package com.ippsd.pochanga.activities

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ippsd.pochanga.R
import com.ippsd.pochanga.users.Json

import kotlinx.android.synthetic.main.activity_players.*

class Start : AppCompatActivity() {

    private val usersPath: String = Environment.getExternalStorageDirectory().toString() + "/POCHA/"
    private val usersFile: String =  usersPath + "users.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players)
        setSupportActionBar(toolbar)

        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun buttonStartGame(@Suppress("UNUSED_PARAMETER") view: View) {
        val json = Json()
        json.load(usersFile)

        val arr = ArrayList<String>()
        for ((k, v) in json.users!!) {
            if (v) arr.add(k)
        }

        val intent = Intent(this, InputScore::class.java)
        intent.putExtra("groups", arr)
        startActivity(intent)
    }

    fun buttonSelectPlayers(@Suppress("UNUSED_PARAMETER") view: View) {
        val intent = Intent(this, UsersActivity::class.java)
        startActivity(intent)
    }

}
