package com.ippsd.pochanga.users

import com.google.gson.Gson
import java.io.File

class Json {
    var users: MutableMap<String, Boolean>? = null
    private var post: Post? = null

    fun load(filename: String) {
        val file = File(filename)
        if (file.exists()) {
            val jsonString: String = file.readText()
            val gson = Gson()
            post = gson.fromJson(jsonString, Post::class.java)
            users = post!!.users
        }
        else {
            users = mutableMapOf()
        }
    }

    fun save(filename: String) {
        post = users?.let { Post(it) }
        val gson = Gson()
        val jsonString: String = gson.toJson(post)
        val file = File(filename)
        if (!file.exists()) { file.createNewFile() }
        file.writeText(jsonString)
    }

    fun add(u: String, c: Boolean = false) {
        if (!u.matches(Regex.fromLiteral("\\s*"))) {
            if (users.isNullOrEmpty()) {
                users = mutableMapOf(Pair(u, c))
            }
            else {
                users?.put(u, c)
            }
        }
    }

    fun del(u: String) {
        if (!users.isNullOrEmpty()){
            if (u in users!!) {
                users!!.remove(u)
            }
        }
    }

    class Post(users: MutableMap<String, Boolean>) {
        var users: MutableMap<String, Boolean>? = users

    }

}