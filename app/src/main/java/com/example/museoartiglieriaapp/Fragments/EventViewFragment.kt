package com.example.museoartiglieriaapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.speech.tts.TextToSpeech
import com.example.museoartiglieriaapp.R
import java.util.Locale

class EventViewFragment : Fragment() {
    private var tts: TextToSpeech? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.event_view_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Gestione del pulsante di chiusura
        view.findViewById<ImageButton>(R.id.close_button)?.setOnClickListener {
            // Torna alla home
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        // Inizializzazione delle view per il TTS
        val ttsButton = view.findViewById<View>(R.id.ttsButton)
        val title = view.findViewById<TextView>(R.id.event_title)
        val eventDay = view.findViewById<TextView>(R.id.event_day)
        val eventMonth = view.findViewById<TextView>(R.id.event_month)

        val description = view.findViewById<TextView>(R.id.event_description_part1)
        val description2 = view.findViewById<TextView>(R.id.event_description_part2)

        // Inizializzazione del TTS
        tts = TextToSpeech(requireContext()) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale.ITALIAN
            }
        }

        // Gestione del click sul bottone TTS
        ttsButton?.setOnClickListener {
            val textToRead = StringBuilder().apply {
                append("${title?.text}. ")
                append("${eventDay?.text} ${eventMonth?.text}. ")
                append("${description?.text}. ")
                append("${description2?.text}")
            }.toString()

            tts?.speak(textToRead, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    override fun onDestroyView() {
        tts?.stop()
        tts?.shutdown()
        super.onDestroyView()
    }
}
