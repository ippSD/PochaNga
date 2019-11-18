package com.ippsd.pochanga.users

class User(private val username: String) {
    var checked: Boolean = false

    fun getUsername(): String {
        return this.username
    }

    companion object {
        fun getUsersFromFile(f: String): ArrayList<User> {
            val json = Json()
            json.load(f)
            val users = json.users
            val n: Int = users?.size!!
            val usersarray: ArrayList<User> = ArrayList(n)
            for ((i, key) in users.keys.sorted().withIndex()) {
                val userI = User(key)
                userI.checked = users[key]!!
                usersarray.add(i, userI)
            }
            return usersarray
        }
    }


}