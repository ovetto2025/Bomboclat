package com.example.museoartiglieriaapp.Fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.museoartiglieriaapp.R
import com.example.museoartiglieriaapp.Models.PortableFirearm

class PortableFirearmsAdapter(
    private val firearms: List<PortableFirearm>,
    private val onFirearmClick: (PortableFirearm) -> Unit
) : RecyclerView.Adapter<PortableFirearmsAdapter.FirearmViewHolder>() {

    class FirearmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.firearm_card_image)
        val nameView: TextView = itemView.findViewById(R.id.firearm_card_name)
        val originView: TextView = itemView.findViewById(R.id.firearm_card_origin)
        val yearView: TextView = itemView.findViewById(R.id.firearm_card_year)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirearmViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_firearm_card, parent, false)
        return FirearmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FirearmViewHolder, position: Int) {
        val firearm = firearms[position]
        
        holder.imageView.setImageResource(R.drawable.placeholder_shape)
        holder.nameView.text = firearm.name
        holder.originView.text = firearm.origin
        holder.yearView.text = firearm.yearOfProduction.toString()
        
        holder.itemView.setOnClickListener {
            onFirearmClick(firearm)
        }
    }

    override fun getItemCount(): Int = firearms.size
} 