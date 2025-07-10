package com.example.museoartiglieriaapp.ViewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase

class MainViewModel: ViewModel() {
    private val firebaseDatabase= FirebaseDatabase.getInstance()
}

// TODO guardare a minuto 1:08:43 in detro per vedere come fare adapter con db