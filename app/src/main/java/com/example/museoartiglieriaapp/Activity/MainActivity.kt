package com.example.museoartiglieriaapp.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.museoartiglieriaapp.R
import com.example.museoartiglieriaapp.databinding.ActivityMainBinding
import com.example.museoartiglieriaapp.Fragments.HomeFragment
import com.example.museoartiglieriaapp.Fragments.InfoFragment
import com.example.museoartiglieriaapp.Fragments.StorageFragment
import com.example.museoartiglieriaapp.Fragments.TicketsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        //recyclerview Carousel
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_activity_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState == null) { // Mostra il fragment solo la prima volta
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    HomeFragment()
                ) // Assicurati di avere un FrameLayout con id "fragment_container" in activity_main.xml
                .commitNow()
        }
    }
}

