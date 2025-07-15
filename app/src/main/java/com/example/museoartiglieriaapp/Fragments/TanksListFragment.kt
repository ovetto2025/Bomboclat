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

class TanksListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tanks_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewTanks)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = TanksAdapter(TANKS_LIST) { tank ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TankDetailFragment.newInstance(tank))
                .addToBackStack(null)
                .commit()
        }
    }
}

// Modello dati Tank
data class Tank(
    val id: String,
    val name: String,
    val image: String,
    val origin: String,
    val yearOfProduction: Int,
    val historicalUse: String,
    val firstAppearance: String,
    val briefHistory: String,
    val trivia: String,
    val technicalSpecifications: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(image)
        parcel.writeString(origin)
        parcel.writeInt(yearOfProduction)
        parcel.writeString(historicalUse)
        parcel.writeString(firstAppearance)
        parcel.writeString(briefHistory)
        parcel.writeString(trivia)
        parcel.writeString(technicalSpecifications)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Tank> {
        override fun createFromParcel(parcel: Parcel): Tank = Tank(parcel)
        override fun newArray(size: Int): Array<Tank?> = arrayOfNulls(size)
    }
}

// Lista tank (mockata dal JSON)
val TANKS_LIST = listOf(
    Tank("m13_40", "M13/40", "placeholder_image_url", "Italy", 1940, "WWII (North Africa)", "Libya, 1940", "The M13/40 was Italy's most common tank during WWII. It was outmatched by British and American designs, but served throughout the North African campaign. Its performance was limited by poor armor and firepower, but it represented Italy's effort to modernize its armored forces. Some were captured and reused by both Allies and Axis forces.", "placeholder_trivia_data", "placeholder_technical_specs"),
    Tank("t34_85", "T-34/85", "placeholder_image_url", "Soviet Union", 1944, "WWII, Korean War, Cold War", "Eastern Front, 1944", "An improved version of the revolutionary T-34, the T-34/85 could finally match German Tigers and Panthers. Its mix of firepower, armor, and mobility made it the Red Army's top tank late in the war. The T-34/85 helped secure Soviet victory in Eastern Europe and became a symbol of Soviet military strength. Its design heavily influenced post-war armored doctrines.", "placeholder_trivia_data", "placeholder_technical_specs"),
    Tank("is2", "IS-2", "placeholder_image_url", "Soviet Union", 1944, "WWII", "Eastern Front, 1944", "Designed to destroy German heavy tanks and fortifications, the IS-2 was the Soviet Union's most powerful tank of the war. Its massive 122 mm gun was deadly against armor and bunkers. The IS-2 led the charge into Berlin in 1945, symbolizing Soviet strength. Post-war, it was gradually replaced but remained in service for decades in allied nations.", "placeholder_trivia_data", "placeholder_technical_specs"),
    Tank("mark_i", "Mark I", "placeholder_image_url", "United Kingdom", 1916, "World War I", "Battle of the Somme, September 15", "The Mark I was the first tank ever used in battle. Developed by the United Kingdom to overcome the trenches and barbed wire of trench warfare, it broke the stalemate on the Western Front. Despite mechanical and logistical challenges, it forever changed land warfare, paving the way for armored warfare in the 20th century.", "placeholder_trivia_data", "placeholder_technical_specs"),
    Tank("panzer_iv", "Panzer IV", "placeholder_image_url", "Germany", 1937, "World War II", "Invasion of Poland, 1939", "The Panzer IV was the backbone of the German armored forces during WWII. Initially a support tank, it was continually upgraded to face newer Allied designs. It fought on all major fronts, from France to the Eastern Front. Its adaptability and ease of production made it the most produced German tank of the war. Later models featured stronger guns and armor to counter Soviet T-34s.", "placeholder_trivia_data", "placeholder_technical_specs"),
    Tank("tiger_i", "Tiger I", "placeholder_image_url", "Nazi Germany", 1942, "WWII", "Leningrad, 1942", "The Tiger I was designed to dominate Allied armor with its powerful 88 mm gun and thick armor. It was nearly invulnerable from the front, but complex and prone to mechanical failure. It became a legendary tank, feared for its killing power, especially in open battle. However, its limited numbers and high cost hindered Germany's ability to mass-deploy it.", "placeholder_trivia_data", "placeholder_technical_specs")
) 