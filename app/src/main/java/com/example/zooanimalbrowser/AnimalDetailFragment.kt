package com.example.zooanimalbrowser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.zooanimalbrowser.models.Animal

class AnimalDetailFragment : Fragment() {

    private lateinit var nameTextView: TextView
    private lateinit var continentTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.animal_detail_fragment, container, false)
        nameTextView = view.findViewById(R.id.name)
        continentTextView = view.findViewById(R.id.continent)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateContentFromArguments()
    }

    private fun updateContentFromArguments() {
        val name = arguments?.getString("name") ?: "N/A"
        val continent = arguments?.getString("continent") ?: "N/A"
        val bgColor = arguments?.getInt("bgColor") ?: android.R.color.transparent
        val textColor = arguments?.getInt("textColor") ?: android.R.color.black

        nameTextView.text = name
        continentTextView.text = continent
        view?.setBackgroundColor(bgColor)
        nameTextView.setTextColor(textColor)
        continentTextView.setTextColor(textColor)
    }

    fun updateContent(animal: Animal, bgColor: Int, textColor: Int) {
        arguments = Bundle().apply {
            putString("name", animal.name)
            putString("continent", animal.continent)
            putInt("bgColor", bgColor)
            putInt("textColor", textColor)
        }

        updateContentFromArguments()
    }
}
