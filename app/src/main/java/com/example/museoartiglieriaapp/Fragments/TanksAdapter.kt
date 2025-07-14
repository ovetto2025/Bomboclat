package com.example.museoartiglieriaapp.Fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.museoartiglieriaapp.R

class TanksAdapter(
    private val tanks: List<Tank>,
    private val onItemClick: (Tank) -> Unit
) : RecyclerView.Adapter<TanksAdapter.TankViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TankViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tank_card, parent, false)
        return TankViewHolder(view)
    }

    override fun onBindViewHolder(holder: TankViewHolder, position: Int) {
        val tank = tanks[position]
        holder.nameTextView.text = tank.name
        // Placeholder per l'immagine
        holder.imageView.setImageResource(R.drawable.placeholder_shape)
        holder.itemView.setOnClickListener { onItemClick(tank) }
    }

    override fun getItemCount(): Int = tanks.size

    class TankViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.tank_image)
        val nameTextView: TextView = itemView.findViewById(R.id.tank_name)
    }
} 