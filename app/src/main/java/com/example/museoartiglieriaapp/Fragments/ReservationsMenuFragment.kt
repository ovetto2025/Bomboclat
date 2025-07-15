package com.example.museoartiglieriaapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.museoartiglieriaapp.R

class ReservationsMenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reservations_menu, container, false)
        view.findViewById<CardView>(R.id.card_book_visit).setOnClickListener {
            // TODO: Apri fragment per prenotare una visita
        }
        view.findViewById<CardView>(R.id.card_your_reservations).setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, YourReservationsFragment())
                .addToBackStack(null)
                .commit()
        }
        return view
    }
} 