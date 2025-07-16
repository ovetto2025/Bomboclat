package com.example.museoartiglieriaapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.museoartiglieriaapp.Fragments.EventView2Fragment
import com.example.museoartiglieriaapp.Fragments.EventViewFragment
import com.example.museoartiglieriaapp.R

class CarouselAdapter(
    private val images: List<Int>,
    private val titles: List<String>,
    private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewItem)
        val titleView: TextView = itemView.findViewById(R.id.imageTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_item, parent, false)
        return CarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.imageView.setImageResource(images[position])
        holder.titleView.text = titles[position]

        // Aggiungi il click listener solo per la prima immagine
        holder.itemView.setOnClickListener {
            if (position == 0) {
                fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, EventViewFragment())
                    .addToBackStack(null)
                    .commit()
            }
            if (position == 1) {
                fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, EventView2Fragment())
                    .addToBackStack(null)
                    .commit()
            }

        }
    }

    override fun getItemCount(): Int = images.size
}
