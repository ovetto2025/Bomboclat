package com.example.museoartiglieriaapp.Fragments

import android.app.Dialog
import android.graphics.Matrix
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.example.museoartiglieriaapp.R

class DialogMapZoom(private val imageResId: Int) : DialogFragment() {
    private var scaleGestureDetector: ScaleGestureDetector? = null
    private var scaleFactor = 1.0f
    private var matrix = Matrix()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_map_zoom, container, false)
        val imageView = view.findViewById<ImageView>(R.id.zoomedMapImage)
        val closeButton = view.findViewById<ImageButton>(R.id.closeButton)
        imageView.setImageResource(imageResId)
        imageView.post {
            // Calcolo la traslazione per centrare l'immagine
            val drawable = imageView.drawable
            if (drawable != null) {
                val viewWidth = imageView.width.toFloat()
                val viewHeight = imageView.height.toFloat()
                val imageWidth = drawable.intrinsicWidth.toFloat()
                val imageHeight = drawable.intrinsicHeight.toFloat()
                val scale = minOf(viewWidth / imageWidth, viewHeight / imageHeight)
                val dx = (viewWidth - imageWidth * scale) / 2f
                val dy = (viewHeight - imageHeight * scale) / 2f
                matrix.setScale(scale, scale)
                matrix.postTranslate(dx, dy)
                imageView.imageMatrix = matrix
                scaleFactor = scale
            }
        }
        imageView.setOnTouchListener { v, event ->
            scaleGestureDetector?.onTouchEvent(event)
            v.performClick()
            true
        }
        scaleGestureDetector = ScaleGestureDetector(requireContext(), object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                scaleFactor *= detector.scaleFactor
                scaleFactor = scaleFactor.coerceIn(1.0f, 5.0f)
                matrix.setScale(scaleFactor, scaleFactor, detector.focusX, detector.focusY)
                imageView.imageMatrix = matrix
                return true
            }
        })
        closeButton.setOnClickListener { dismiss() }
        return view
    }
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
} 