package com.example.museoartiglieriaapp.Fragments

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.museoartiglieriaapp.R
import com.example.museoartiglieriaapp.Models.PortableFirearm
import com.google.gson.Gson
import org.json.JSONObject
import android.content.Context

class PortableFirearmsListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_portable_firearms_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewFirearms)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        // Carica la lista dal JSON
        val firearms = loadPortableFirearmsFromJson(requireContext())
        recyclerView.adapter = PortableFirearmsAdapter(firearms) { firearm ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PortableFirearmDetailFragment.newInstance(firearm))
                .addToBackStack(null)
                .commit()
        }
    }
}

fun loadPortableFirearmsFromJson(context: Context): List<PortableFirearm> {
    val jsonString = context.assets.open("artifacts.json").bufferedReader().use { it.readText() }
    val jsonObject = JSONObject(jsonString)
    val firearms = mutableListOf<PortableFirearm>()
    val keys = jsonObject.keys()
    while (keys.hasNext()) {
        val key = keys.next()
        val obj = jsonObject.getJSONObject(key)
        val category = obj.optString("category", "")
        if (category == "rifle" || category == "pistol" || category == "submachine_gun") {
            val techSpecs = obj.optJSONObject("technical_specifications")
            val yearStr = techSpecs?.optString("year") ?: techSpecs?.optString("yearOfProduction")
            val year = yearStr?.filter { it.isDigit() }?.toIntOrNull() ?: 0
            val origin = techSpecs?.optString("origin") ?: techSpecs?.optString("country") ?: ""
            val use = obj.optString("historicalUse", "")
            val firstApp = obj.optString("firstAppearance", "")
            val briefHistory = obj.optString("brief_history", "")
            val triviaArray = obj.optJSONArray("trivia")
            val trivia = if (triviaArray != null) {
                (0 until triviaArray.length()).joinToString("\n\n") { i -> triviaArray.getString(i) }
            } else ""
            val techSpecsString = techSpecs?.let {
                it.keys().asSequence().joinToString("\n") { k -> "${k.replace('_', ' ').replaceFirstChar { c -> c.uppercase() }}: ${it.optString(k)}" }
            } ?: ""
            val firearm = PortableFirearm(
                id = key,
                name = key,
                image = "", // L'immagine viene gestita nella detail con la mappa url
                origin = origin,
                yearOfProduction = year,
                historicalUse = use,
                firstAppearance = firstApp,
                briefHistory = briefHistory,
                trivia = trivia,
                technicalSpecifications = techSpecsString
            )
            firearms.add(firearm)
        }
    }
    return firearms
}

// Lista armi portatili (mockata dal JSON)
