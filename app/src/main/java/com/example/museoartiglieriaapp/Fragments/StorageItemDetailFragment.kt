package com.example.museoartiglieriaapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.museoartiglieriaapp.Models.StorageItem
import com.example.museoartiglieriaapp.R

class StorageItemDetailFragment : Fragment() {
    companion object {
        private const val ARG_ITEM = "arg_item"
        fun newInstance(item: StorageItem): StorageItemDetailFragment {
            val fragment = StorageItemDetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_ITEM, item)
            fragment.arguments = args
            return fragment
        }
    }

    private var item: StorageItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        item = arguments?.getParcelable(ARG_ITEM)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_storage_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = view.findViewById<ImageView>(R.id.item_detail_image)
        val nameView = view.findViewById<TextView>(R.id.item_detail_name)
        val originView = view.findViewById<TextView>(R.id.item_detail_origin)
        val yearView = view.findViewById<TextView>(R.id.item_detail_year)
        val useView = view.findViewById<TextView>(R.id.item_detail_use)
        val firstAppView = view.findViewById<TextView>(R.id.item_detail_first_appearance)
        val historyView = view.findViewById<TextView>(R.id.item_detail_brief_history)

        item?.let {
            imageView.setImageResource(R.drawable.placeholder_shape)
            nameView.text = it.name
            originView.text = "Origin: ${it.origin}"
            yearView.text = "Year: ${it.yearOfProduction}"
            useView.text = "Use: ${it.historicalUse}"
            firstAppView.text = "First Appearance: ${it.firstAppearance}"
            historyView.text = it.briefHistory
        }
    }
} 