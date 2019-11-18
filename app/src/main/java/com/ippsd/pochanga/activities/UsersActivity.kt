package com.ippsd.pochanga.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.widget.ListView
import com.ippsd.pochanga.R
import com.ippsd.pochanga.users.Json
import com.ippsd.pochanga.users.User
import com.ippsd.pochanga.users.UserAdapter
import kotlinx.android.synthetic.main.activity_users.*
import kotlinx.android.synthetic.main.popup_add_user.view.*
import java.io.File
import android.view.inputmethod.InputMethodManager.HIDE_IMPLICIT_ONLY
import android.content.Context
import android.view.inputmethod.InputMethodManager


class UsersActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private val usersPath: String = Environment.getExternalStorageDirectory().toString() + "/POCHA/"
    private val usersFile: String =  usersPath + "users.json"

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        setSupportActionBar(toolbar)
        listView = findViewById(R.id.listview_users)

        // Short Click
        listView.setOnItemClickListener { parent, _, position, _ ->
            val adp: UserAdapter = parent.adapter as UserAdapter
            val element: User = adp.getItem(position) as User
            element.checked = !element.checked
            val json = Json()
            json.load(usersFile)
            json.users!![element.getUsername()] = element.checked
            json.save(usersFile)
            this.update()
        }

        // Long Click
        listView.setOnItemLongClickListener { parent, _, position, _ ->
            val adp: UserAdapter = parent.adapter as UserAdapter
            val element: User = adp.getItem(position) as User
            val user = element.getUsername()
            adp.delItemId(position)
            val json = Json()
            json.load(usersFile)
            json.del(user)
            json.save(usersFile)
            this.update()
            true
        }

        val path = File(usersPath)
        if (!path.exists()) path.mkdir()

        fab.setOnClickListener {
            val diagView = LayoutInflater.from(this).inflate(R.layout.popup_add_user, null)
            diagView.dialoguser.requestFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, HIDE_IMPLICIT_ONLY)
            val diagBuilder = AlertDialog.Builder(this).setView(diagView).setTitle("Add User")
            val alertDialog = diagBuilder.show()
            diagView.dialogbutton.setOnClickListener { view ->
                imm.hideSoftInputFromWindow(view.windowToken, 0)
                alertDialog.dismiss()
                val user = diagView.dialoguser.text.toString()
                val json = Json()
                json.load(usersFile)
                json.add(user)
                json.save(usersFile)
                update()
            }
        }
        update()
    }

    private fun update() {
        val userList: ArrayList<User> = User.getUsersFromFile(usersFile)
        val adapter = UserAdapter(this, userList)
        listView.adapter = adapter
    }
}
