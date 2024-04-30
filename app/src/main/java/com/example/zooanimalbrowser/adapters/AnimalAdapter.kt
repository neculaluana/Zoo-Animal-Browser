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
import android.graphics.drawable.GradientDrawable

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

        val backgroundDrawable = holder.view.context.resources.getDrawable(R.drawable.rectangle_oval_shape, null).mutate() as GradientDrawable

        val color = when (animal.continent) {
            "Europe" -> Color.parseColor("#355E3B") //verde
            "Africa" -> Color.parseColor("#FADA5E") //galben
            "Asia" -> Color.parseColor("#D2042D") //rosu
            "North America" -> Color.parseColor("#8B4513") // maro
            "South America" -> Color.parseColor("#FF7518") // portocaliu
            "Australia" -> Color.parseColor("#800080") // mov
            "Antarctica" -> Color.parseColor("#6495ED") //albastru
            else -> Color.TRANSPARENT // default
        }
        backgroundDrawable.setColor(color)

        holder.view.background = backgroundDrawable

        val textColor = when (animal.continent) {
            "Europe", "Australia", "Asia", "North America", "Antarctica" -> Color.WHITE
            "South America", "Africa" -> Color.BLACK
            else -> Color.BLACK
        }
        nameTextView.setTextColor(textColor)
        continentTextView.setTextColor(textColor)

        when (animal.continent) {
            "Europe" -> {
                nameTextView.gravity = Gravity.START
                continentTextView.gravity = Gravity.BOTTOM
                separator.visibility = View.GONE
            }
            "Africa"-> {
                nameTextView.gravity = Gravity.START
                continentTextView.gravity = Gravity.START
                separator.visibility = View.VISIBLE
            }
            "Asia" -> {
                nameTextView.gravity = Gravity.CENTER_HORIZONTAL
                continentTextView.gravity = Gravity.CENTER_HORIZONTAL
                separator.visibility = View.VISIBLE
            }
            "Antarctica" -> {
                nameTextView.gravity = Gravity.CENTER_HORIZONTAL
                continentTextView.gravity = Gravity.CENTER_HORIZONTAL
                separator.visibility = View.VISIBLE
            }
            "Australia" -> {
                nameTextView.gravity = Gravity.CENTER_HORIZONTAL
                continentTextView.gravity = Gravity.CENTER_HORIZONTAL
                separator.visibility = View.GONE
            }
            "North America" -> {
                nameTextView.gravity = Gravity.END
                continentTextView.gravity = Gravity.END
                separator.visibility = View.GONE
            }
            "South America" -> {
                nameTextView.gravity = Gravity.END
                continentTextView.gravity = Gravity.END
                separator.visibility = View.VISIBLE
            }
        }
    }



    override fun getItemCount() = animals.size
}
