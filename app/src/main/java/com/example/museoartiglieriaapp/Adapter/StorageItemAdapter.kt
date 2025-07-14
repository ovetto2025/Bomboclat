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
        
        holder.imageView.setImageResource(R.drawable.placeholder_shape)
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