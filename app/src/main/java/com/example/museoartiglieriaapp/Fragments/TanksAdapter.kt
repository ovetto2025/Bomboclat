package com.example.museoartiglieriaapp.Fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
        // Mappa nome tank -> URL (come nel dettaglio)
        val artifactImageUrls = mapOf(
            "PANZER IV" to "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiQXVrDDkJ4PLTZK9F28lYTsRY8uFxA-Lq4TRXBGVMthRDsNLcQ_VykVgN-7fkA2ofhY7IP0Xe4TH3m0D9mdSPEZ45gs1xfxbd5gsejBhPqScVHcOSaYIcJUhmEzxLSawFVg3ifZiLLkB9I/s1600/0+0+0+42100076342_f653e3d6da_b.jpg",
            "TIGER I" to "https://upload.wikimedia.org/wikipedia/commons/6/6e/Bundesarchiv_Bild_101I-299-1805-16%2C_Nordfrankreich%2C_Panzer_VI_%28Tiger_I%29_cropped.jpg",
            "IS-2" to "https://preview.redd.it/future-tanks-v0-8lkc7e051qia1.jpg?width=320&crop=smart&auto=webp&s=fcb17cbc659034c89317dd2715802e034526c908",
            "MARK I" to "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiENouirmJo3DeyiHP4cSOU_EyrzMMoBzGXgl-IuOdup8Ih1Kxo34pgzDd7qaoWxBQucWXil1mPN-TJiO1RUv880xB-0EIvWVmWu1gXBWMKI7PbfKU6aQxa1W4nvaWxwfckU1mTlYDLnJSg/s1600/carro+armato+con+scritta+in+russo.jpg",
            "M13/40" to "https://www.lasecondaguerramondiale.org/wp-content/uploads/2019/08/91c14cbfa735c2522cedce0eda8810b9.jpg",
            "T-34/85" to "https://eu-wotp.wgcdn.co/dcont/fb/image/t-34-85-austrian-army-museum.jpg"
        )
        val imageUrl = artifactImageUrls[tank.name]
        if (imageUrl != null) {
            Glide.with(holder.imageView.context)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder_shape)
                .error(R.drawable.placeholder_shape)
                .into(holder.imageView)
        } else {
            holder.imageView.setImageResource(R.drawable.placeholder_shape)
        }
        holder.itemView.setOnClickListener { onItemClick(tank) }
    }

    override fun getItemCount(): Int = tanks.size

    class TankViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.tank_image)
        val nameTextView: TextView = itemView.findViewById(R.id.tank_name)
    }
} 