package com.example.museoartiglieriaapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.museoartiglieriaapp.R

class CarouselDetailFragment : Fragment() {

    companion object {
        private const val ARG_IMAGE_RESOURCE = "image_resource"
        private const val ARG_TITLE = "title"
        private const val ARG_DESCRIPTION = "description"

        fun newInstance(imageResource: Int, title: String, description: String): CarouselDetailFragment {
            return CarouselDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_IMAGE_RESOURCE, imageResource)
                    putString(ARG_TITLE, title)
                    putString(ARG_DESCRIPTION, description)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_carousel_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageResource = arguments?.getInt(ARG_IMAGE_RESOURCE, R.drawable.ic_launcher_background) ?: R.drawable.ic_launcher_background
        val title = arguments?.getString(ARG_TITLE, "") ?: ""
        val description = arguments?.getString(ARG_DESCRIPTION, "") ?: ""

        val imageView = view.findViewById<ImageView>(R.id.detailImageView)
        val titleTextView = view.findViewById<TextView>(R.id.detailTitleTextView)
        val descriptionTextView = view.findViewById<TextView>(R.id.detailDescriptionTextView)

        imageView.setImageResource(imageResource)
        titleTextView.text = title
        descriptionTextView.text = description
    }
} 