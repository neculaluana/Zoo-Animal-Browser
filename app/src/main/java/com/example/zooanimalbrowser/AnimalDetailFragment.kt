package com.example.zooanimalbrowser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class AnimalDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.animal_detail_fragment, container, false)

        val nameTextView: TextView = view.findViewById(R.id.name)
        val continentTextView: TextView = view.findViewById(R.id.continent)
        val bgColor = arguments?.getInt("bgColor") ?: android.R.color.transparent
        val textColor = arguments?.getInt("textColor") ?: android.R.color.black
        nameTextView.text = arguments?.getString("name")
        continentTextView.text = arguments?.getString("continent")
        view.setBackgroundColor(bgColor)
        nameTextView.setTextColor(textColor)
        continentTextView.setTextColor(textColor)

        return view
    }
}