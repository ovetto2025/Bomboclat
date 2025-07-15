package com.example.museoartiglieriaapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.museoartiglieriaapp.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import androidx.core.content.ContextCompat
import android.widget.Toast
import android.widget.TextView
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.example.museoartiglieriaapp.Model.ReservationRepository
import com.example.museoartiglieriaapp.Model.Reservation
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Locale
import android.widget.DatePicker

class NewReservationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_newreservation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<MaterialButton>(R.id.checkoutButton).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PaymentFragment())
                .addToBackStack(null)
                .commit()
        }

        val cardSingle = view.findViewById<MaterialCardView>(R.id.card_single)
        val cardFamily = view.findViewById<MaterialCardView>(R.id.card_family)
        val cardVip = view.findViewById<MaterialCardView>(R.id.card_vip)
        val defaultColor = ContextCompat.getColor(requireContext(), R.color.grey_button_background)
        val selectedColor = ContextCompat.getColor(requireContext(), R.color.button_time_selector)

        val cards = listOf(cardSingle, cardFamily, cardVip)
        val textColorDefault = ContextCompat.getColor(requireContext(), R.color.black)
        val textColorSelected = ContextCompat.getColor(requireContext(), R.color.white)

        // Card selection logic
        val cardSingleText = cardSingle.findViewById<TextView>(R.id.card_single_text)
        val cardSinglePrice = cardSingle.findViewById<TextView>(R.id.card_single_price)
        val cardFamilyText = cardFamily.findViewById<TextView>(R.id.card_family_text)
        val cardFamilyPrice = cardFamily.findViewById<TextView>(R.id.card_family_price)
        val cardVipText = cardVip.findViewById<TextView>(R.id.card_vip_text)
        val cardVipPrice = cardVip.findViewById<TextView>(R.id.card_vip_price)
        Log.d("DEBUG", "cardSingleText: $cardSingleText, cardSinglePrice: $cardSinglePrice, cardFamilyText: $cardFamilyText, cardFamilyPrice: $cardFamilyPrice, cardVipText: $cardVipText, cardVipPrice: $cardVipPrice")
        if (cardSingleText == null || cardSinglePrice == null || cardFamilyText == null || cardFamilyPrice == null || cardVipText == null || cardVipPrice == null) {
            Toast.makeText(requireContext(), "Errore: uno o più TextView delle card non trovati!", Toast.LENGTH_LONG).show()
            return
        }
        val cardTextViews = listOf(cardSingleText, cardSinglePrice, cardFamilyText, cardFamilyPrice, cardVipText, cardVipPrice)

        fun setCardTextColor(selectedCard: MaterialCardView) {
            val allCards = listOf(cardSingle, cardFamily, cardVip)
            allCards.forEach { card ->
                val t1 = card.findViewById<TextView>(R.id.card_single_text) ?: card.findViewById<TextView>(R.id.card_family_text) ?: card.findViewById<TextView>(R.id.card_vip_text)
                val t2 = card.findViewById<TextView>(R.id.card_single_price) ?: card.findViewById<TextView>(R.id.card_family_price) ?: card.findViewById<TextView>(R.id.card_vip_price)
                t1?.setTextColor(textColorDefault)
                t2?.setTextColor(textColorDefault)
            }
            val t1 = selectedCard.findViewById<TextView>(R.id.card_single_text) ?: selectedCard.findViewById<TextView>(R.id.card_family_text) ?: selectedCard.findViewById<TextView>(R.id.card_vip_text)
            val t2 = selectedCard.findViewById<TextView>(R.id.card_single_price) ?: selectedCard.findViewById<TextView>(R.id.card_family_price) ?: selectedCard.findViewById<TextView>(R.id.card_vip_price)
            t1?.setTextColor(textColorSelected)
            t2?.setTextColor(textColorSelected)
        }

        var selectedTicketType: String? = null
        var selectedTime: String? = null
        var selectedLocation: String? = null

        for ((i, card) in cards.withIndex()) {
            card.setOnClickListener {
                cards.forEach { it.setCardBackgroundColor(defaultColor) }
                card.setCardBackgroundColor(selectedColor)
                setCardTextColor(card)
                selectedTicketType = when (i) {
                    0 -> "Single"
                    1 -> "Family"
                    2 -> "VIP"
                    else -> null
                }
                val datePicker = view.findViewById<DatePicker>(R.id.date_picker)
                ReservationRepository.draft = Reservation(
                    ticketType = selectedTicketType ?: "",
                    time = selectedTime ?: "",
                    location = selectedLocation ?: "",
                    date = getSelectedDate(datePicker),
                    ticketImage = getTicketImage(selectedTicketType)
                )
            }
        }
        // Imposta colore testo default all'avvio
        setCardTextColor(cardSingle)

        // Logica selezione orari
        val timeGrid = view.findViewById<android.widget.GridLayout>(R.id.timeGrid)
        val timeButtons = mutableListOf<MaterialButton>()
        for (i in 0 until timeGrid.childCount) {
            val child = timeGrid.getChildAt(i)
            if (child is MaterialButton) {
                timeButtons.add(child)
            }
        }
        timeButtons.forEach { btn ->
            btn.setOnClickListener {
                timeButtons.forEach {
                    it.setBackgroundColor(defaultColor)
                    it.setTextColor(textColorDefault)
                }
                btn.setBackgroundColor(selectedColor)
                btn.setTextColor(textColorSelected)
                selectedTime = btn.text.toString()
                val datePicker = view.findViewById<DatePicker>(R.id.date_picker)
                ReservationRepository.draft = Reservation(
                    ticketType = selectedTicketType ?: "",
                    time = selectedTime ?: "",
                    location = selectedLocation ?: "",
                    date = getSelectedDate(datePicker),
                    ticketImage = getTicketImage(selectedTicketType)
                )
            }
        }
        // Imposta colore testo default all'avvio
        timeButtons.forEach { it.setTextColor(textColorDefault) }

        // Dropdown location logic
        val dropdownButton = view.findViewById<androidx.cardview.widget.CardView>(R.id.dropdownButton)
        val dropdownMenu = view.findViewById<androidx.cardview.widget.CardView>(R.id.dropdownMenu)
        val dropdownTitle = view.findViewById<TextView>(R.id.dropdownTitle)
        val arrowIcon = view.findViewById<ImageView>(R.id.arrowIcon)
        val location1 = view.findViewById<TextView>(R.id.location1)
        val location2 = view.findViewById<TextView>(R.id.location2)

        dropdownButton.setOnClickListener {
            if (dropdownMenu.isVisible) {
                arrowIcon.setImageResource(R.drawable.ic_arrow_up)
                dropdownMenu.visibility = View.GONE
            } else {
                arrowIcon.setImageResource(R.drawable.ic_arrow_down)
                dropdownMenu.visibility = View.VISIBLE
            }
        }

        val locationViews = listOf(location1, location2)
        val locationClickListener = View.OnClickListener { v ->
            val loc = (v as TextView).text.toString()
            selectedLocation = loc
            dropdownTitle.text = loc
            dropdownMenu.visibility = View.GONE
            arrowIcon.setImageResource(R.drawable.ic_arrow_up)
            locationViews.forEach { it.isSelected = false }
            v.isSelected = true
            val datePicker = view.findViewById<DatePicker>(R.id.date_picker)
            ReservationRepository.draft = Reservation(
                ticketType = selectedTicketType ?: "",
                time = selectedTime ?: "",
                location = selectedLocation ?: "",
                date = getSelectedDate(datePicker),
                ticketImage = getTicketImage(selectedTicketType)
            )
        }
        location1.setOnClickListener(locationClickListener)
        location2.setOnClickListener(locationClickListener)
    }

    private fun getSelectedDate(datePicker: DatePicker): String {
        val day = datePicker.dayOfMonth
        val month = datePicker.month + 1
        val year = datePicker.year
        return String.format("%02d/%02d/%04d", day, month, year)
    }

    private fun getTicketImage(type: String?): String {
        return when (type) {
            "Single" -> "ic_single_ticket"
            "Family" -> "ic_family"
            "VIP" -> "ic_vip"
            else -> "ic_single_ticket"
        }
    }
} 