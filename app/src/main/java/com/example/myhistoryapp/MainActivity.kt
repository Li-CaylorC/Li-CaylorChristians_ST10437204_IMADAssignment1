package com.example.myhistoryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.title= "Home"

        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val edAge = findViewById<EditText>(R.id.edAge)
        val edRange = findViewById<EditText>(R.id.edRange)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        //read the json file from the raw directory/folder
        val jsonData = application.resources.openRawResource(
            applicationContext.resources.getIdentifier(
                "famous_people",
                "raw", applicationContext.packageName
            )
        ).bufferedReader().use { it.readText() }
        //get the json object that was read
        val outPut = JSONObject(jsonData)
        //store all the found people
        val aryPersons = JSONArray()

        btnSearch?.setOnClickListener{
            tvResult.text=""

            //convert the object into a json array
            val person = outPut.getJSONArray("famous_person") as JSONArray

            //iterate through the json array
            for(i in 0 until person.length()){
                //declare variable and set value
                val name = person.getJSONObject(i).getString("name")
                val age = person.getJSONObject(i).getString("age")
                val description = person.getJSONObject(i).getString("description")
                val died = person.getJSONObject(i).getString("date died")

                var inputAge = edAge.text.toString().toInt()
                var dataAge = age.toInt()
                var dataRange = edRange.text.toString().toInt()
                if (inputAge <20){
                    Toast.makeText(applicationContext,"Only ages greater than or equal to 20 allowed",Toast.LENGTH_LONG).show()
                }
               else if((inputAge >= (dataAge-dataRange)) and (inputAge <= (dataAge+dataRange))){

                    aryPersons.put(person.getJSONObject(i))
                    val abs = aryPersons.getJSONObject(0).getString("name")
                }
            }
            val j = aryPersons.length()
            if(j >0){
//                displayPeople(aryPersons)
//                val intent = Intent(this, DisplayActivity::class.java)
//                    .putExtra("FAMOUS_PEOPLE", aryPersons.toString())
//                startActivity(intent)

                val intent: Intent = Intent(
                    this,
                    DisplayActivity::class.java
                )
                intent.putExtra("FAMOUS_PEOPLE", aryPersons.toString())
                startActivity(intent)
            }else{
                Toast.makeText(applicationContext,"No result to display, " +
                        "try to increase the range and try again",Toast.LENGTH_LONG).show()
            }
        }
        btnClear?.setOnClickListener {
            edAge.text.clear()
        }

        //1. main activity
        //2. display activity
        //3. layout xml - cardview to show
        //4. recyclerview adapter
        //5. raw - json data file
        //6. update strings file in values
        //7. update manifest file to include internet connection
        //8. picasso library


//        fun displayPeople(JSONArray ary){
//            val intent = Intent(this, DisplayActivity::class.java)
//                .putExtra("FAMOUS_PEOPLE", ary)
//                startActivity(intent)
//            }
//        }
    }
}