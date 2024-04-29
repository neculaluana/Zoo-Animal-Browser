package com.example.zooanimalbrowser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zooanimalbrowser.adapters.AnimalAdapter
import com.example.zooanimalbrowser.models.Animal

class AnimalListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val animals: MutableList<Animal> = mutableListOf(
        Animal("Tanuki", "Asia"),
        Animal("Koala", "Australia"),
        Animal("Lion", "Africa"),
        Animal("Elephant", "Africa"),
        Animal("Rhinoceros", "Africa"),
        Animal("Zebra", "Africa"),
        Animal("Giraffe", "Africa"),
        Animal("Cheetah", "Africa"),
        Animal("Leopard", "Africa"),
        Animal("Hippopotamus", "Africa"),
        Animal("Chimpanzee", "Africa"),
        Animal("Gorilla", "Africa"),
        Animal("Kangaroo", "Australia"),
        Animal("Echidna", "Australia"),
        Animal("Wombat", "Australia"),
        Animal("Dingo", "Australia"),
        Animal("Tasmanian Devil", "Australia"),
        Animal("Platypus", "Australia"),
        Animal("Emu", "Australia"),
        Animal("Kookaburra", "Australia"),
        Animal("Quokka", "Australia"),
        Animal("Panda", "Asia"),
        Animal("Tiger", "Asia"),
        Animal("Orangutan", "Asia"),
        Animal("Gibbon", "Asia"),
        Animal("Komodo Dragon", "Asia"),
        Animal("Grizzly Bear", "North America"),
        Animal("Moose", "North America"),
        Animal("Bald Eagle", "North America"),
        Animal("Raccoon", "North America"),
        Animal("Beaver", "North America"),
        Animal("Bison", "North America"),
        Animal("Coyote", "North America"),
        Animal("Puma", "North America"),
        Animal("Jaguar", "South America"),
        Animal("Sloth", "South America"),
        Animal("Capybara", "South America"),
        Animal("Anaconda", "South America"),
        Animal("Llama", "South America"),
        Animal("Alpaca", "South America"),
        Animal("Macaw", "South America"),
        Animal("Toucan", "South America"),
        Animal("Piranha", "South America"),
        Animal("Emperor Penguin", "Antarctica"),
        Animal("Adelie Penguin", "Antarctica"),
        Animal("Weddell Seal", "Antarctica"),
        Animal("Leopard Seal", "Antarctica"),
        Animal("Orca", "Antarctica"),
        Animal("Albatross", "Antarctica"),
        Animal("Brown Bear", "Europe"),
        Animal("Red Fox", "Europe"),
        Animal("Wild Boar", "Europe"),
        Animal("European Roe Deer", "Europe"),
        Animal("Wolf", "Europe"),
        Animal("Golden Eagle", "Europe"),
        Animal("Red Panda", "Asia"),
        Animal("Gaur", "Asia")
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.animal_list_fragment, container, false)

        recyclerView = view.findViewById(R.id.animalList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = AnimalAdapter(getAnimals())

        return view
    }

    private fun getAnimals(): List<Animal> {
        return animals
    }
}
