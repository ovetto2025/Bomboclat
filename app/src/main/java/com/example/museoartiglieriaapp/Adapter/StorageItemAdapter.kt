package com.example.museoartiglieriaapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.museoartiglieriaapp.Models.StorageItem
import com.example.museoartiglieriaapp.R

class StorageItemAdapter(
    private val items: List<StorageItem>,
    private val onItemClick: (StorageItem) -> Unit
) : RecyclerView.Adapter<StorageItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.item_card_image)
        val nameView: TextView = itemView.findViewById(R.id.item_card_name)
        val originView: TextView = itemView.findViewById(R.id.item_card_origin)
        val yearView: TextView = itemView.findViewById(R.id.item_card_year)
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
    }

    override fun getItemCount(): Int = items.size
} 