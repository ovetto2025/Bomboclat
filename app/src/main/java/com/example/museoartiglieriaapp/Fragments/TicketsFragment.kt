package com.example.museoartiglieriaapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.museoartiglieriaapp.R
import com.google.android.material.card.MaterialCardView

class CameraFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<MaterialCardView>(R.id.card_book_visit).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NewReservationFragment())
                .addToBackStack(null)
                .commit()
        }
        view.findViewById<MaterialCardView>(R.id.card_your_reservations).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, YourReservationsFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}