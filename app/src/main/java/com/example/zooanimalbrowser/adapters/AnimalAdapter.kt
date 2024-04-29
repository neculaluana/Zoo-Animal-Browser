package com.example.zooanimalbrowser.adapters

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zooanimalbrowser.R
import com.example.zooanimalbrowser.models.Animal
import android.graphics.Color

class AnimalAdapter(private val animals: List<Animal>) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    class AnimalViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = if (viewType == 0) {
            LayoutInflater.from(parent.context).inflate(R.layout.animal_item_vertical, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.animal_item_horizontal, parent, false)
        }
        return AnimalViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (animals[position].continent == "Asia") {
            1 // horizontal
        } else {
            0 // vertical
        }
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animals[position]

        val nameTextView = holder.view.findViewById<TextView>(R.id.name)
        val continentTextView = holder.view.findViewById<TextView>(R.id.continent)

        nameTextView.text = animal.name
        continentTextView.text = animal.continent

        val separator = holder.view.findViewById<View>(R.id.separator)
        when (animal.continent) {
            "Europe" -> {
                holder.view.setBackgroundColor(Color.GREEN)
                nameTextView.gravity = Gravity.START
                continentTextView.gravity = Gravity.BOTTOM
                separator.visibility = View.GONE
            }
            "Africa" -> {
                holder.view.setBackgroundColor(Color.YELLOW)
                nameTextView.gravity = Gravity.START
                continentTextView.gravity = Gravity.START
                separator.visibility = View.VISIBLE
            }
            "Asia" -> {
                holder.view.setBackgroundColor(Color.RED)
                nameTextView.gravity = Gravity.CENTER_HORIZONTAL
                continentTextView.gravity = Gravity.CENTER_HORIZONTAL
                separator.visibility = View.VISIBLE
            }
            "North America" -> {
                holder.view.setBackgroundColor(Color.parseColor("#8B4513")) // maro
                nameTextView.gravity = Gravity.END
                continentTextView.gravity = Gravity.END
                separator.visibility = View.GONE
            }
            "South America" -> {
                holder.view.setBackgroundColor(Color.parseColor("#FF7518")) // portocaliu
                nameTextView.gravity = Gravity.END
                continentTextView.gravity = Gravity.END
                separator.visibility = View.VISIBLE
            }
            "Australia" -> {
                holder.view.setBackgroundColor(Color.parseColor("#800080")) // mov
                nameTextView.gravity = Gravity.CENTER_HORIZONTAL
                continentTextView.gravity = Gravity.CENTER_HORIZONTAL
                separator.visibility = View.GONE
            }
            "Antarctica" -> {
                holder.view.setBackgroundColor(Color.BLUE)
                nameTextView.gravity = Gravity.CENTER_HORIZONTAL
                continentTextView.gravity = Gravity.CENTER_HORIZONTAL
                separator.visibility = View.VISIBLE
            }
        }
    }


    override fun getItemCount() = animals.size
}
