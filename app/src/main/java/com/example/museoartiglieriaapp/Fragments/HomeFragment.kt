package com.example.museoartiglieriaapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.museoartiglieriaapp.Adapters.CarouselAdapter
import com.example.museoartiglieriaapp.R
import com.google.android.material.carousel.CarouselLayoutManager

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup del carosello
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = CarouselLayoutManager()

        // Lista delle immagini e dei titoli corrispondenti
        val images = listOf(
            R.drawable.event_backgroud,
            R.drawable.event_2
        )

        val titles = listOf(
            "Artillery & Innovation",
            "Shh, let the cannon speak!"
        )

        // Inizializzazione dell'adapter con immagini, titoli e fragmentManager
        val adapter = CarouselAdapter(images, titles, parentFragmentManager)
        recyclerView.adapter = adapter

        // Setup degli altri elementi della home...
        setupHomeButtons(view)
    }

    private fun setupHomeButtons(view: View) {
        // Gestione del click sulla card del museo istituzionale
        view.findViewById<View>(R.id.card_corso_lecce)?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DetailsInstitutionalFragment())
                .addToBackStack(null)
                .commit()
        }

        // Gestione del click sulla card del deposito
        view.findViewById<View>(R.id.card_corso_ferraris)?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DetailsMuseumFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
