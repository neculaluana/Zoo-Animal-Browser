package com.example.zooanimalbrowser.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zooanimalbrowser.R
import com.example.zooanimalbrowser.data.models.AnimalDBModel

class AnimalAdapter(
    private val animals: List<AnimalDBModel>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(animal: AnimalDBModel)
        fun onItemDelete(animal: AnimalDBModel)
    }

    class AnimalViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(animal: AnimalDBModel, clickListener: OnItemClickListener) {
            view.setOnClickListener {
                clickListener.onItemClick(animal)
            }
            view.findViewById<ImageView>(R.id.deleteItem).setOnClickListener {
                clickListener.onItemDelete(animal)
            }

            val nameTextView = view.findViewById<TextView>(R.id.name)
            val continentTextView = view.findViewById<TextView>(R.id.continent)

            nameTextView.text = animal.name
            continentTextView.text = animal.continent
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.animal_item_vertical, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.bind(animals[position], itemClickListener)
    }

    override fun getItemCount() = animals.size
}
