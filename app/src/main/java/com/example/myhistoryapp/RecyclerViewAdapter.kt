package com.example.myhistoryapp

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.json.JSONArray

class RecyclerViewAdapter(private val personList : JSONArray)  : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var image: ImageView = itemView.findViewById(R.id.img)
        var txtName: TextView = itemView.findViewById(R.id.name)
        var txtAge: TextView = itemView.findViewById(R.id.age)
        var txtDeath: TextView = itemView.findViewById(R.id.death)
        var txtDesc: TextView = itemView.findViewById(R.id.desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val v:View = LayoutInflater.from(parent.context).inflate(R.layout.card_cell,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.image.setImageURI(null)
        val img = personList.getJSONObject(position).getString("image")
        val name = personList.getJSONObject(position).getString("name")
        val age = personList.getJSONObject(position).getString("age")
        val died = personList.getJSONObject(position).getString("date died")
        val desc = personList.getJSONObject(position).getString("description")

        if(img.trim() != ""){
            val imgUri = Uri.parse(img)
            Picasso.get().load(imgUri).placeholder(R.drawable.notavailable).into(holder.image)
        }else {
            holder.image.setImageResource(R.drawable.notavailable)
        }
        holder.txtName.text = name

        val sAge: String = "Age: $age"
        holder.txtAge.text = sAge

        val sDied: String = "Died: $died"
        holder.txtDeath.text = sDied
        holder.txtDesc.text = desc
    }

    override fun getItemCount(): Int {
        return personList.length()
    }

}