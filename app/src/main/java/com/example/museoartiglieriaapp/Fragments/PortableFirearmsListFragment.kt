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
        recyclerView.adapter = PortableFirearmsAdapter(PORTABLE_FIREARMS_LIST) { firearm ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PortableFirearmDetailFragment.newInstance(firearm))
                .addToBackStack(null)
                .commit()
        }
    }
}

// Modello dati PortableFirearm
data class PortableFirearm(
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

    companion object CREATOR : Parcelable.Creator<PortableFirearm> {
        override fun createFromParcel(parcel: Parcel): PortableFirearm = PortableFirearm(parcel)
        override fun newArray(size: Int): Array<PortableFirearm?> = arrayOfNulls(size)
    }
}

// Lista armi portatili (mockata dal JSON)
val PORTABLE_FIREARMS_LIST = listOf(
    PortableFirearm("mp40", "MP40", "placeholder_image_url", "Nazi Germany", 1938, "WWII", "Early WWII (Poland, 1939)", "The MP40 was the most recognizable German SMG of WWII, known for its folding stock and use by paratroopers and infantry alike. It was designed for ease of use and mass production, becoming an icon of German infantry firepower. Its influence persists in modern SMG designs worldwide.", "placeholder_trivia_data", "placeholder_technical_specs"),
    PortableFirearm("m91_30", "M91/30", "placeholder_image_url", "Soviet Union / Russia", 1930, "WWI (early models), WWII, Cold War", "Russian Imperial Army, 1891 (original design)", "One of the most widely produced military rifles in history, the Mosin-Nagant M91/30 was famed for its durability and accuracy under harsh conditions. It played a decisive role on the Eastern Front of WWII and was widely distributed to Soviet allies. Its design remained largely unchanged for decades, attesting to its effectiveness.", "placeholder_trivia_data", "placeholder_technical_specs"),
    PortableFirearm("sten_gun", "STEN GUN", "placeholder_image_url", "United Kingdom", 1941, "WWII, post-war conflicts", "Early WWII", "Designed for mass production under wartime constraints, the Sten was cheap, easy to manufacture, and widely issued to British and Commonwealth forces. Despite some reliability issues, it was praised for its simplicity and effectiveness in guerrilla warfare and urban combat. Its design influenced many postwar submachine guns.", "placeholder_trivia_data", "placeholder_technical_specs"),
    PortableFirearm("m1934", "M1934", "placeholder_image_url", "Italy", 1934, "WWII, Post-war", "Second Italo-Ethiopian War, 1935", "The Beretta M1934 was widely issued to Italian forces during World War II. Known for its compact size and reliability, it was also used by police and paramilitary units in Italy and abroad. Its simple design made it easy to maintain even in harsh battlefield conditions. Post-war, it continued to serve many countries as a standard sidearm.", "placeholder_trivia_data", "placeholder_technical_specs"),
    PortableFirearm("m91", "M91", "placeholder_image_url", "Italy", 1891, "WWI, WWII", "Italian Army service, 1891", "The Carcano M91 rifle served as the backbone of Italian infantry for over 50 years. Its design influenced many variants and was Italy's primary infantry rifle through two world wars. Despite criticism over its stopping power, it was valued for its accuracy and ruggedness. The rifle also saw limited use in other countries post-WWII.", "placeholder_trivia_data", "placeholder_technical_specs"),
    PortableFirearm("m1a1", "M1A1", "placeholder_image_url", "United States", 1928, "WWII, Prohibition Era", "Used by law enforcement and military from late 1920s", "Famous for use in WWII and by gangsters during the Prohibition, the Tommy Gun combined high fire rate with portability and ruggedness. It was highly regarded by soldiers for close-quarter combat. The weapon's distinctive profile made it a symbol of American military and popular culture.", "placeholder_trivia_data", "placeholder_technical_specs")
) 