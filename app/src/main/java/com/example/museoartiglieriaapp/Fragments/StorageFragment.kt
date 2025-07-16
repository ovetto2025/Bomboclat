package com.example.museoartiglieriaapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.museoartiglieriaapp.R


class StorageFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_storage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.button_storage_archive).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, StorageArchiveFragment())
                .addToBackStack(null)
                .commit()
        }
    }

}

