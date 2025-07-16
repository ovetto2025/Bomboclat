package com.example.museoartiglieriaapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.museoartiglieriaapp.R

class StorageArchiveFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_storage_archive, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Listener per le 4 card
        view.findViewById<View>(R.id.card1).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CategoryListFragment.newInstance("tanks"))
                .addToBackStack(null)
                .commit()
        }
        view.findViewById<View>(R.id.card2).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CategoryListFragment.newInstance("firearms"))
                .addToBackStack(null)
                .commit()
        }
        view.findViewById<View>(R.id.card4).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CategoryListFragment.newInstance("edged_weapons"))
                .addToBackStack(null)
                .commit()
        }
        view.findViewById<View>(R.id.card5).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CategoryListFragment.newInstance("military_equipment"))
                .addToBackStack(null)
                .commit()
        }
    }
} 