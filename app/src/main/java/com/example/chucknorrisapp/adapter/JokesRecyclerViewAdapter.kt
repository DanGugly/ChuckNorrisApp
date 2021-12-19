package com.example.chucknorrisapp.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.chucknorrisapp.R
import com.example.chucknorrisapp.model.Jokes
import com.example.chucknorrisapp.model.Value

class JokesRecyclerViewAdapter(
    private val values: MutableList<Value> = mutableListOf()
) : RecyclerView.Adapter<JokeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {

        val classicView = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_joke,
            parent,
            false
        )
        return JokeViewHolder(classicView)

    }

    fun loadJokes(jokes : List<Jokes>){
        values.addAll(jokes[0].value)
        Log.d("Adapter", "Jokes: $values")
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id.toString()
        Log.d("Adapter", "OnBindVH: $item")
        holder.contentView.text = item.joke
    }

    override fun getItemCount(): Int = values.size

}
class JokeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var idView: TextView = itemView.findViewById(R.id.item_number)
    var contentView: TextView = itemView.findViewById(R.id.content)
}