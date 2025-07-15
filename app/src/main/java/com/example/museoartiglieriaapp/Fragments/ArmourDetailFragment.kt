package com.example.museoartiglieriaapp.Fragments

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.museoartiglieriaapp.R
import com.example.museoartiglieriaapp.Model.Armour
import java.util.Locale

class ArmourDetailFragment : Fragment(), TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    private var title: String? = null
    private var year: String? = null
    private var imageRes: Int = R.drawable.ic_launcher_foreground
    private var description: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            year = it.getString(ARG_YEAR)
            imageRes = it.getInt(ARG_IMAGE_RES, R.drawable.ic_launcher_foreground)
            description = it.getString(ARG_DESCRIPTION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_armour_detail, container, false)
        view.findViewById<TextView>(R.id.detail_title).text = title
        view.findViewById<TextView>(R.id.detail_year).text = year
        view.findViewById<ImageView>(R.id.detail_image).setImageResource(imageRes)
        view.findViewById<TextView>(R.id.detail_description).text = description

        view.findViewById<ImageButton>(R.id.btn_back).setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        tts = TextToSpeech(requireContext(), this)
        view.findViewById<ImageButton>(R.id.btn_tts_title).setOnClickListener {
            speak(title ?: "")
        }
        view.findViewById<ImageButton>(R.id.btn_tts_description).setOnClickListener {
            speak(description ?: "")
        }
        return view
    }

    private fun speak(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale.getDefault()
        }
    }

    override fun onDestroyView() {
        tts?.stop()
        tts?.shutdown()
        super.onDestroyView()
    }

    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_YEAR = "year"
        private const val ARG_IMAGE_RES = "imageRes"
        private const val ARG_DESCRIPTION = "description"

        fun newInstance(armour: Armour): ArmourDetailFragment {
            val fragment = ArmourDetailFragment()
            val args = Bundle()
            args.putString(ARG_TITLE, armour.title)
            args.putString(ARG_YEAR, armour.year)
            args.putInt(ARG_IMAGE_RES, armour.imageRes)
            args.putString(ARG_DESCRIPTION, armour.description)
            fragment.arguments = args
            return fragment
        }
    }
} 