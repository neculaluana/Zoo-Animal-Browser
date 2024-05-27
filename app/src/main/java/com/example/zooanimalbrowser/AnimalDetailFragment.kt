package com.example.zooanimalbrowser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.zooanimalbrowser.data.models.AnimalDBModel

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
        view.setBackgroundResource(R.drawable.gradient_background)
        (activity as? MainActivity)?.showInputFields(false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateContentFromArguments()
    }

    private fun updateContentFromArguments() {
        val name = arguments?.getString("name") ?: "N/A"
        val continent = arguments?.getString("continent") ?: "N/A"

        nameTextView.text = name
        continentTextView.text = continent
        view?.setBackgroundResource(R.drawable.gradient_background)
    }

    fun updateContent(animal: AnimalDBModel) {
        arguments = Bundle().apply {
            putString("name", animal.name)
            putString("continent", animal.continent)
        }

        updateContentFromArguments()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        (activity as? MainActivity)?.showInputFields(true)
    }

}
