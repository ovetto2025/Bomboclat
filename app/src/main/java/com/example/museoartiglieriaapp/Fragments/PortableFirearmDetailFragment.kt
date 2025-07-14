package com.example.museoartiglieriaapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.museoartiglieriaapp.R

class PortableFirearmDetailFragment : Fragment() {
    companion object {
        private const val ARG_FIREARM = "arg_firearm"
        fun newInstance(firearm: PortableFirearm): PortableFirearmDetailFragment {
            val fragment = PortableFirearmDetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_FIREARM, firearm)
            fragment.arguments = args
            return fragment
        }
    }

    private var firearm: PortableFirearm? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firearm = arguments?.getParcelable(ARG_FIREARM)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_firearm_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = view.findViewById<ImageView>(R.id.firearm_detail_image)
        val nameView = view.findViewById<TextView>(R.id.firearm_detail_name)
        val originView = view.findViewById<TextView>(R.id.firearm_detail_origin)
        val yearView = view.findViewById<TextView>(R.id.firearm_detail_year)
        val useView = view.findViewById<TextView>(R.id.firearm_detail_use)
        val firstAppView = view.findViewById<TextView>(R.id.firearm_detail_first_appearance)
        val historyView = view.findViewById<TextView>(R.id.firearm_detail_brief_history)

        firearm?.let {
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