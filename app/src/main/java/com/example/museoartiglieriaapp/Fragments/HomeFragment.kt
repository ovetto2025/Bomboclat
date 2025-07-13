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

        // Listener per le card
        binding.cardCorsoLecce.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LecceDetailFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.cardCorsoFerraris.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FerrarisDetailFragment())
                .addToBackStack(null)
                .commit()
        }
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

        val adapter = CarouselAdapter(imageList) { position ->
            // Apri il fragment di dettaglio quando si clicca su un elemento del carousel
            openCarouselDetail(position)
        }
        binding.recyclerView.adapter = adapter
    }

    private fun openCarouselDetail(position: Int) {
        // Dati per le descrizioni degli eventi
        val eventTitles = listOf(
            "Mostra Storica",
            "Esposizione Temporanea", 
            "Evento Speciale"
        )
        
        val eventDescriptions = listOf(
            "Una mostra dedicata alla storia dell'artiglieria italiana, con pezzi unici risalenti al XIX secolo. " +
            "Scopri le innovazioni tecnologiche che hanno caratterizzato l'evoluzione dell'artiglieria militare " +
            "attraverso i secoli, con particolare attenzione ai periodi delle guerre mondiali.",
            
            "Esposizione temporanea di modelli in scala e diorami storici. " +
            "I visitatori potranno ammirare ricostruzioni dettagliate di battaglie famose " +
            "e comprendere meglio le strategie militari dell'epoca.",
            
            "Evento speciale con dimostrazioni dal vivo e visite guidate tematiche. " +
            "I nostri esperti ti accompagneranno in un viaggio attraverso la storia " +
            "dell'artiglieria, con focus su tecniche di restauro e conservazione."
        )
        
        val imageList = listOf(
            R.drawable.event_backgroud,
            R.drawable.event_backgroud,
            R.drawable.event_backgroud
        )
        
        // Crea il fragment di dettaglio
        val detailFragment = CarouselDetailFragment.newInstance(
            imageList[position],
            eventTitles[position],
            eventDescriptions[position]
        )
        
        // Sostituisci il fragment corrente con quello di dettaglio
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailFragment, "CAROUSEL_DETAIL_TAG")
            .addToBackStack("CAROUSEL_DETAIL_TAG")
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Pulisci il riferimento al binding per evitare memory leak
    }
}
