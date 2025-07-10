package com.example.museoartiglieriaapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.museoartiglieriaapp.Adapter.CarouselAdapter
import com.example.museoartiglieriaapp.R
import com.example.museoartiglieriaapp.databinding.FragmentHomeBinding
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    // Questa proprietà è valida solo tra onCreateView e onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCarousel()
    }

    private fun setupCarousel() {
        binding.recyclerView.setHasFixedSize(true)

        binding.recyclerView.layoutManager = CarouselLayoutManager()

        CarouselSnapHelper().attachToRecyclerView(binding.recyclerView)

        val imageList = mutableListOf<Int>()
        imageList.add(R.drawable.event_backgroud)
        imageList.add(R.drawable.event_backgroud)
        imageList.add(R.drawable.event_backgroud)
        // Aggiungi altre immagini se necessario

        val adapter = CarouselAdapter(imageList) // Assicurati che il tuo CarouselAdapter sia corretto
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Pulisci il riferimento al binding per evitare memory leak
    }
}
