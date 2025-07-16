package com.example.museoartiglieriaapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.museoartiglieriaapp.Models.StorageItem
import com.example.museoartiglieriaapp.R
import android.speech.tts.TextToSpeech
import java.util.Locale
import com.bumptech.glide.Glide

class StorageItemAdapter(
    private val items: List<StorageItem>,
    private val onItemClick: (StorageItem) -> Unit
) : RecyclerView.Adapter<StorageItemAdapter.ItemViewHolder>() {

    private var tts: TextToSpeech? = null

    fun setTTS(tts: TextToSpeech) {
        this.tts = tts
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.item_card_image)
        val nameView: TextView = itemView.findViewById(R.id.item_card_name)
        val originView: TextView = itemView.findViewById(R.id.item_card_origin)
        val yearView: TextView = itemView.findViewById(R.id.item_card_year)
        val ttsButton: ImageButton = itemView.findViewById(R.id.tts_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_storage_card, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        
        // Mappa nome artefatto -> URL (stessa dei dettagli)
        val artifactImageUrls = mapOf(
            // Tanks
            "M13/40" to "https://www.lasecondaguerramondiale.org/wp-content/uploads/2019/08/91c14cbfa735c2522cedce0eda8810b9.jpg",
            "T-34/85" to "https://eu-wotp.wgcdn.co/dcont/fb/image/t-34-85-austrian-army-museum.jpg",
            "Tiger I" to "https://upload.wikimedia.org/wikipedia/commons/6/6e/Bundesarchiv_Bild_101I-299-1805-16%2C_Nordfrankreich%2C_Panzer_VI_%28Tiger_I%29_cropped.jpg",
            // Firearms
            "MP40" to "https://www.denix.es/it/catalogo/7170/g/denix-Mitragliatrice-MP40--Germania-1940.jpg",
            "M91/30" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzgLARVmfP3ul6Q_WhDF2uwoUeSYLAb3rpZ-iikVOl9_-orPWbS3MZ9C2rr4LH_vqXH-Y&usqp=CAU",
            "M1A1" to "https://www.denix.es/it/catalogo/7075/p/denix-Submachine-M1928A1--USA-1918.jpg",
            // Edged Weapons
            "Seiten" to "https://www.kubel1943.it/foto/pompire.jpg",
            // Military Equipment
            "Helmet" to "https://www.arsvalue.com/Upl/Auctions/968/1912/224081/1009-0.jpg",
            "M33" to "https://www.militariainroma.com/wp-content/uploads/2025/04/IMG_9350.jpeg",
            "M1" to "https://shorturl.at/froIZ",
            // Artillery (esempi placeholder)
            "Sample Artillery 1" to "",
            "Sample Artillery 2" to "",
            "Sample Artillery 3" to "",
            // Aircraft (esempi placeholder)
            "Sample Aircraft 1" to "",
            "Sample Aircraft 2" to "",
            "Sample Aircraft 3" to ""
        )
        val imageUrl = artifactImageUrls[item.name]
        if (imageUrl != null) {
            Glide.with(holder.imageView.context)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder_shape)
                .error(R.drawable.placeholder_shape)
                .into(holder.imageView)
        } else {
            holder.imageView.setImageResource(R.drawable.placeholder_shape)
        }
        holder.nameView.text = item.name
        holder.originView.text = item.origin
        holder.yearView.text = item.yearOfProduction
        
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
        holder.ttsButton.setOnClickListener {
            tts?.speak("${item.name}. ${item.briefHistory}", TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    override fun getItemCount(): Int = items.size
} 