package com.example.museoartiglieriaapp.Activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.museoartiglieriaapp.R
import androidx.cardview.widget.CardView
import com.example.museoartiglieriaapp.Model.Armour

class ArmourAdapter(
    private val items: List<Armour>,
    private val onClick: (Armour) -> Unit
) : RecyclerView.Adapter<ArmourAdapter.ArmourViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArmourViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_card, parent, false)
        return ArmourViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArmourViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ArmourViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.item_image)
        private val title: TextView = itemView.findViewById(R.id.item_title)
        private val year: TextView = itemView.findViewById(R.id.item_year)

        fun bind(armour: Armour) {
            image.setImageResource(armour.imageRes)
            title.text = armour.title
            year.text = armour.year
            itemView.setOnClickListener { onClick(armour) }
        }
    }
} 