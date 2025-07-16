package com.example.museoartiglieriaapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.museoartiglieriaapp.R

class TankDetailFragment : Fragment() {
    companion object {
        private const val ARG_TANK = "arg_tank"
        fun newInstance(tank: Tank): TankDetailFragment {
            val fragment = TankDetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_TANK, tank)
            fragment.arguments = args
            return fragment
        }
    }

    private var tank: Tank? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tank = arguments?.getParcelable(ARG_TANK)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tank_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = view.findViewById<ImageView>(R.id.tank_detail_image)
        val nameView = view.findViewById<TextView>(R.id.tank_detail_name)
        val originView = view.findViewById<TextView>(R.id.tank_detail_origin)
        val yearView = view.findViewById<TextView>(R.id.tank_detail_year)
        val useView = view.findViewById<TextView>(R.id.tank_detail_use)
        val firstAppView = view.findViewById<TextView>(R.id.tank_detail_first_appearance)
        val historyView = view.findViewById<TextView>(R.id.tank_detail_brief_history)

        tank?.let {
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