package com.example.zooanimalbrowser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zooanimalbrowser.adapters.AnimalAdapter
import com.example.zooanimalbrowser.adapters.AnimalAdapter.OnItemClickListener
import com.example.zooanimalbrowser.data.models.AnimalDBModel
import com.example.zooanimalbrowser.data.tasks.DeleteAnimalTask
import com.example.zooanimalbrowser.data.tasks.GetAllAnimalsTask

class AnimalListFragment : Fragment(), OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private val animals: MutableList<AnimalDBModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.animal_list_fragment, container, false)

        recyclerView = view.findViewById(R.id.animalList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = AnimalAdapter(animals, this)

        loadAnimals()

        return view
    }

    private fun loadAnimals() {
        val app = activity?.application as? ZooApplication
        app?.let {
            GetAllAnimalsTask(it) { loadedAnimals ->
                animals.clear()
                animals.addAll(loadedAnimals)
                recyclerView.adapter?.notifyDataSetChanged()
            }.execute()
        }
    }

    override fun onItemClick(animal: AnimalDBModel, bgcolor: Int, textColor: Int) {
        val fragmentManager = activity?.supportFragmentManager
        var detailFragment = fragmentManager?.findFragmentById(R.id.fragmentContainer) as? AnimalDetailFragment

        if (detailFragment == null) {
            detailFragment = AnimalDetailFragment().apply {
                arguments = Bundle().apply {
                    putString("name", animal.name)
                    putString("continent", animal.continent)
                    putInt("bgColor", bgcolor)
                    putInt("textColor", textColor)
                }
            }
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, detailFragment)
                ?.addToBackStack(null)
                ?.commit()
        } else {
            detailFragment.updateContent(animal, bgcolor, textColor)
        }
    }

    override fun onItemDelete(animal: AnimalDBModel) {
        val app = activity?.application as? ZooApplication
        app?.let {
            DeleteAnimalTask(it) {
                updateAnimalList()
            }.execute(animal)
        }
    }

    fun updateAnimalList() {
        loadAnimals()
    }
}
