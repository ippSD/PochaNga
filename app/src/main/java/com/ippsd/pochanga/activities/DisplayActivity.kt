package com.ippsd.pochanga.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.ShareActionProvider
import android.text.method.ScrollingMovementMethod
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.ippsd.pochanga.R
import kotlinx.android.synthetic.main.activity_display.*


class DisplayActivity : AppCompatActivity() {

    enum class TYPES {
        X, N
    }

    private var users: ArrayList<String>? = null
    private var scores: ArrayList<ArrayList<Int>>? = null
    private var shareActionProvider: ShareActionProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        setSupportActionBar(toolbar)

        @Suppress("UNCHECKED_CAST")
        users = intent.extras!!.get("users") as ArrayList<String>
        @Suppress("UNCHECKED_CAST")
        scores = intent.extras!!.get("scores") as ArrayList<ArrayList<Int>>

        fab_share.setOnClickListener {v: View ->
            val popup = PopupMenu(this, v)
            val menu = popup.menu
            popup.menuInflater.inflate(R.menu.menu_share, menu)
            val menuItem: MenuItem = menu.findItem(R.id.menu_item_share)
            shareActionProvider = MenuItemCompat.getActionProvider(menuItem) as ShareActionProvider?
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, scores2text(diffScores2Scores(scores!!)))
            this.setShareIntent(intent)
            popup.show()
        }

        update()
    }

    private fun setShareIntent(shareIntent: Intent) {
        shareActionProvider?.setShareIntent(shareIntent)
    }

    private fun update() {
        val text: String = scores2text(diffScores2Scores(scores!!))
        val textView: TextView = findViewById(R.id.textview_display)
        textView.text = text
        textView.movementMethod = ScrollingMovementMethod()
    }

    private fun diffScores2Scores(scores: ArrayList<ArrayList<Int>>): ArrayList<ArrayList<Int>>{
        val rounds: Int = scores.size
        val user_no: Int = scores[0].size
        var scoresAll: ArrayList<ArrayList<Int>> = ArrayList()
        var scores0: ArrayList<Int> = ArrayList()

        for (i in 0 until user_no) {
            scores0.add(0)
        }


        for (i in 0 until rounds) {
            val scoresNow: ArrayList<Int> = scores[i]
            if (i == 0) {
                scoresAll.add(scores0)
            }
            else {
                var tmp: ArrayList<Int> = ArrayList(user_no)
                for ( k in 0 until user_no ) { tmp.add(scoresAll[i-1][k]) }
                scoresAll.add(tmp)
            }
            for (j in 0 until user_no) {
                val pointRaw = scoresNow[j]
                val point = if (pointRaw < 0) pointRaw * 5  else 10 + pointRaw * 5
                scoresAll[i][j] += point
            }
        }
        println(scoresAll)
        return scoresAll
    }

    private fun scores2text (scores_t: ArrayList<ArrayList<Int>>): String {
        var text: String = ""
        for (user in users!!) {
            text += user + "|"
        }
        text = text.dropLast(1)
        text += "\n"

        for (row in scores_t) {
            var user_idx: Int = 0
            for (score in row) {
                val d: Int = users!![user_idx].length
                val fmtStr: String = "%" + d.toString() + "d|"
                text += java.lang.String.format(fmtStr, score) as String
                user_idx += 1
            }
            text = text.dropLast(1)
            text += "\n"
        }
        return text
    }

}
