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
            ReservationRepository.draft?.let {
                ReservationRepository.addReservation(it)
                ReservationRepository.draft = null
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, YourReservationsFragment())
                .addToBackStack(null)
                .commit()
        }
    }
} 