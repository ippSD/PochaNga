package com.ippsd.pochanga

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ippsd.pochanga.activities.Start

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonStart(@Suppress("UNUSED_PARAMETER") v: View) {
        val intent = Intent(this, Start::class.java)
        startActivity(intent)
    }
}
