package com.example.museoartiglieriaapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.museoartiglieriaapp.R

class CarouselAdapter(
    private val imageList: List<Int>,
    private val onItemClick: ((Int) -> Unit)? = null // Callback opzionale
) : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_item, parent, false)
        return CarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.imageView.setImageResource(imageList[position])
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    inner class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewItem)
    }
} 