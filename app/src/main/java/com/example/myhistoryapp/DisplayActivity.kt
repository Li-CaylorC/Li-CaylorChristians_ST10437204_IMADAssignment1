package com.example.myhistoryapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONException


class DisplayActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        this.title= "Search Results"

        //navigate back
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //get the recyclerview control
        val recView = findViewById<RecyclerView>(R.id.recView)

        //get result from search
        val jsonArray = intent.getStringExtra("FAMOUS_PEOPLE")
        try {
            //declare new array
            val array: JSONArray = JSONArray(jsonArray)
            //parse result into new array
            //val abs = array.getJSONObject(0).getString("name")
            recView.layoutManager = LinearLayoutManager(this)
            recView.setHasFixedSize(true)
            recView.adapter = RecyclerViewAdapter(array)


        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}