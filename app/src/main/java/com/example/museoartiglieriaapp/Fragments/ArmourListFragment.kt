package com.example.museoartiglieriaapp.Fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.museoartiglieriaapp.Activity.ArmourAdapter
import com.example.museoartiglieriaapp.R
import com.example.museoartiglieriaapp.Model.Armour

class ArmourListFragment : Fragment() {
    private lateinit var adapter: ArmourAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var btnBack: ImageButton

    // Dati finti per esempio
    private val armourList = listOf(
        Armour("Elmo medievale", "Italia, XV secolo", R.drawable.museum_details_back, "Elmo in ferro battuto utilizzato dai cavalieri italiani nel tardo Medioevo. Proteggeva la testa durante i tornei e le battaglie."),
        Armour("Corazza romana", "Roma, I secolo d.C.", R.drawable.group_131, "Corazza lorica segmentata in metallo, tipica dei legionari romani. Garantiva protezione e mobilità durante le campagne militari."),
        Armour("Scudo vichingo", "Scandinavia, X secolo", R.drawable.storage_back_2, "Scudo rotondo in legno rinforzato con ferro, usato dai guerrieri vichinghi. Decorato spesso con motivi simbolici."),
        Armour("Armatura samurai", "Giappone, XVII secolo", R.drawable.storage_back_1, "Armatura in lamine di ferro e cuoio laccato, indossata dai samurai giapponesi. Leggera e resistente, permetteva grande agilità."),
        Armour("Elmo greco corinzio", "Grecia, V secolo a.C.", R.drawable.image_istitutional, "Elmo in bronzo con copertura per naso e guance, simbolo dei guerrieri opliti greci."),
        Armour("Corazza napoleonica", "Francia, 1805", R.drawable.istitutional_home_btn, "Corazza in acciaio indossata dai corazzieri francesi durante le guerre napoleoniche.")
    )
    private var filteredList = armourList.toMutableList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_armour_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        searchEditText = view.findViewById(R.id.search_edittext)
        btnBack = view.findViewById(R.id.btn_back)

        adapter = ArmourAdapter(filteredList) { armour ->
            // Apri il dettaglio come fragment (lo implementeremo dopo)
            val fragment = ArmourDetailFragment.newInstance(armour)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter

        btnBack.setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterList(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        return view
    }

    private fun filterList(query: String) {
        filteredList.clear()
        filteredList.addAll(
            if (query.isEmpty()) armourList
            else armourList.filter { it.title.contains(query, ignoreCase = true) }
        )
        adapter.notifyDataSetChanged()
    }
} 