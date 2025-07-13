package com.example.museoartiglieriaapp.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.museoartiglieriaapp.R
import com.example.museoartiglieriaapp.databinding.InfoFragmentBinding // Importa la classe di binding generata

class InfoFragment : Fragment() {

    private var _binding: InfoFragmentBinding? = null
    // Questa proprietà è un "non-nullable" getter per _binding.
    // Accederà sempre a un valore non nullo dopo onCreateView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Gonfia il layout usando il binding
        _binding = InfoFragmentBinding.inflate(inflater, container, false)
        return binding.root // Restituisci la root view dal binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ora puoi accedere alle viste direttamente tramite l'oggetto binding
        binding.backButton.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }

        binding.mapImageLecce.setOnClickListener {
            val address = getString(R.string.corso_lecce_address)
            openGoogleMaps(address)
        }

        binding.mapImageFerraris.setOnClickListener {
            val address = getString(R.string.institutional_address_long) // Usa la stringa corretta per Ferraris
            openGoogleMaps(address)
        }

        binding.phone_number.setOnClickListener {
            val phoneNumber = getString(R.string.phone_number)
            dialPhoneNumber(phoneNumber)
        }

        binding.email_segrmuseoart.setOnClickListener {
            val emailAddress = getString(R.string.email_segrmuseoart)
            sendEmail(emailAddress)
        }

        binding.email_infomuseoart.setOnClickListener {
            val emailAddress = getString(R.string.email_infomuseoart)
            sendEmail(emailAddress)
        }
    }

    // Le funzioni helper (openGoogleMaps, dialPhoneNumber, sendEmail) rimangono le stesse

    private fun openGoogleMaps(address: String) {
        val gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address))
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        try {
            startActivity(mapIntent)
        } catch (e: Exception) {
            Toast.makeText(context, "Google Maps non trovato. Impossibile aprire la mappa.", Toast.LENGTH_SHORT).show()
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?q=" + Uri.encode(address)))
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

    override fun onDestroyView() {
        super.onDestroyView()
        // È importante annullare il riferimento al binding quando la vista del fragment viene distrutta
        // per evitare memory leak.
        _binding = null
    }
}