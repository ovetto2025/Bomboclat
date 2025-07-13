package com.example.museoartiglieriaapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.CompoundBarcodeView

class CameraFragment : Fragment() {
    private lateinit var barcodeView: CompoundBarcodeView
    private var lastScanned: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_camera, container, false)
        barcodeView = view.findViewById(R.id.barcode_scanner)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (allPermissionsGranted()) {
            startScanner()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                10
            )
        }
    }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            requireContext(), Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

    private fun startScanner() {
        barcodeView.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult?) {
                val qr = result?.text
                if (qr != null && qr != lastScanned) {
                    lastScanned = qr
                    Toast.makeText(requireContext(), "QR trovato: $qr", Toast.LENGTH_SHORT).show()
                    // Qui puoi gestire il risultato (es: callback, navigazione, ecc)
                }
            }
        })
        barcodeView.resume()
    }

    override fun onResume() {
        super.onResume()
        if (::barcodeView.isInitialized) barcodeView.resume()
    }

    override fun onPause() {
        super.onPause()
        if (::barcodeView.isInitialized) barcodeView.pause()
    }
}

