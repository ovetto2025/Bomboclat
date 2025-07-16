package com.example.museoartiglieriaapp.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView // Importa TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.museoartiglieriaapp.R

class InfoFragment : Fragment() {

    // Non abbiamo più bisogno delle variabili _binding e binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Gonfia il layout come al solito, ma senza il binding automatico
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Riferimenti alle viste usando findViewById

        val mapImageLecce: ImageView = view.findViewById(R.id.map_image_lecce)
        val mapImageFerraris: ImageView = view.findViewById(R.id.map_image_ferraris)
        val phoneNumberTextView: TextView = view.findViewById(R.id.phone_number)
        val emailSegrMuseoArtTextView: TextView = view.findViewById(R.id.email_segrmuseoart)
        val emailInfoMuseoArtTextView: TextView = view.findViewById(R.id.email_infomuseoart)




        mapImageLecce.setOnClickListener {
            val address = getString(R.string.corso_lecce_address)
            openGoogleMaps(address)
        }

        mapImageFerraris.setOnClickListener {
            val address = getString(R.string.institutional_address_long)
            openGoogleMaps(address)
        }

        phoneNumberTextView.setOnClickListener {
            val phoneNumber = getString(R.string.phone_number)
            dialPhoneNumber(phoneNumber)
        }

        emailSegrMuseoArtTextView.setOnClickListener {
            val emailAddress = getString(R.string.email_segrmuseoart)
            sendEmail(emailAddress)
        }

        emailInfoMuseoArtTextView.setOnClickListener {
            val emailAddress = getString(R.string.email_infomuseoart)
            sendEmail(emailAddress)
        }
    }

    // Le funzioni helper (openGoogleMaps, dialPhoneNumber, sendEmail) rimangono identiche
    private fun openGoogleMaps(address: String) {
        val gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address))
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        try {
            startActivity(mapIntent)
        } catch (e: Exception) {
            Toast.makeText(context, "Google Maps non trovato. Impossibile aprire la mappa.", Toast.LENGTH_SHORT).show()
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://googleusercontent.com/maps.google.com/2" + Uri.encode(address)))
            startActivity(webIntent)
        }
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(context, "Nessuna app per telefonate trovata.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendEmail(emailAddress: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
            putExtra(Intent.EXTRA_SUBJECT, "Informazioni da Artillery Museum App")
        }
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(context, "Nessuna app di posta trovata.", Toast.LENGTH_SHORT).show()
        }
    }

}