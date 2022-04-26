package com.b0r1ngx.p0495

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.b0r1ngx.p0495.data.History
import com.b0r1ngx.p0495.utility.TinyDB
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        tinyDB = TinyDB(applicationContext)
        globalHistory = tinyDB.getListObject("History", History::class.java) as ArrayList<History>
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onStop() {
        super.onStop()
        Log.d("Test", "MainActivity onStop()")
        tinyDB.putListObject("History", globalHistory as ArrayList<Any>)
    }

    companion object {
        var score = 20
        var gameScore = 0
        var indexHistory = 0

        @SuppressLint("StaticFieldLeak")
        lateinit var tinyDB: TinyDB
        lateinit var globalHistory: ArrayList<History>
    }
}