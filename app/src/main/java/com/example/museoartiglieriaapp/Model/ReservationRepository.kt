package com.example.museoartiglieriaapp.Model

object ReservationRepository {
    private val reservations = mutableListOf<Reservation>()
    var draft: Reservation? = null

    fun addReservation(reservation: Reservation) {
        reservations.add(reservation)
    }

    fun getReservations(): List<Reservation> = reservations
} 