package com.example.museoartiglieriaapp.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.museoartiglieriaapp.R
import com.google.android.material.button.MaterialButton
import com.example.museoartiglieriaapp.Model.ReservationRepository
import com.example.museoartiglieriaapp.Fragments.YourReservationsFragment

class PaymentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val expirationInput = view.findViewById<EditText>(R.id.expirationInput)
        val cardHolderInput = view.findViewById<EditText>(R.id.cardHolderInput)
        val cardNumberInput = view.findViewById<EditText>(R.id.cardNumberInput)
        val cvvInput = view.findViewById<EditText>(R.id.cvvInput)
        expirationInput.addTextChangedListener(object : TextWatcher {
            var isEditing = false
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (isEditing) return
                isEditing = true
                val text = s.toString().replace("/", "")
                if (text.length > 2) {
                    val month = text.substring(0, 2)
                    val year = text.substring(2)
                    expirationInput.setText("$month/$year")
                    expirationInput.setSelection(expirationInput.text.length)
                } else {
                    expirationInput.setText(text)
                    expirationInput.setSelection(text.length)
                }
                isEditing = false
            }
        })

        val checkoutButton = view.findViewById<MaterialButton>(R.id.checkoutButton)
        checkoutButton.setOnClickListener {
            val cardHolder = cardHolderInput.text.toString().trim()
            val cardNumber = cardNumberInput.text.toString().trim()
            val expiration = expirationInput.text.toString().trim()
            val cvv = cvvInput.text.toString().trim()
            if (cardHolder.isEmpty() || cardNumber.isEmpty() || expiration.isEmpty() || cvv.isEmpty()) {
                android.widget.Toast.makeText(requireContext(), "Compila tutti i campi della carta", android.widget.Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (cardNumber.length != 16 || !cardNumber.all { it.isDigit() }) {
                android.widget.Toast.makeText(requireContext(), "Il numero della carta deve essere di 16 cifre", android.widget.Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!expiration.matches(Regex("\\d{2}/\\d{2}"))) {
                android.widget.Toast.makeText(requireContext(), "La scadenza deve essere nel formato MM/YY", android.widget.Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                val parts = expiration.split("/")
                val month = parts[0].toIntOrNull()
                val year = parts[1].toIntOrNull()
                if (month == null || year == null || month !in 1..12) {
                    android.widget.Toast.makeText(requireContext(), "Mese di scadenza non valido", android.widget.Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            if (cvv.length != 3 || !cvv.all { it.isDigit() }) {
                android.widget.Toast.makeText(requireContext(), "Il CVV deve essere di 3 cifre", android.widget.Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            ReservationRepository.draft?.let {
                ReservationRepository.addReservation(it)
                ReservationRepository.draft = null
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, YourReservationsFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<View>(R.id.back_button)?.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
} 