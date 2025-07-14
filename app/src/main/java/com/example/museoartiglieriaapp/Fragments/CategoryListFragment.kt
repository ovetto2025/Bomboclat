package com.example.museoartiglieriaapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.museoartiglieriaapp.Adapter.StorageItemAdapter
import com.example.museoartiglieriaapp.Models.StorageItem
import com.example.museoartiglieriaapp.R
import com.example.museoartiglieriaapp.Repository.StorageRepository
import android.speech.tts.TextToSpeech
import java.util.Locale

class CategoryListFragment : Fragment() {
    private var tts: TextToSpeech? = null
    private var adapter: StorageItemAdapter? = null

    companion object {
        private const val ARG_CATEGORY = "arg_category"
        
        fun newInstance(category: String): CategoryListFragment {
            val fragment = CategoryListFragment()
            val args = Bundle()
            args.putString(ARG_CATEGORY, category)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val category = arguments?.getString(ARG_CATEGORY) ?: "tanks"
        val items = StorageRepository.getItemsByCategory(category)
        
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCategory)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = StorageItemAdapter(items) { item ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, StorageItemDetailFragment.newInstance(item))
                .addToBackStack(null)
                .commit()
        }
        recyclerView.adapter = adapter
        tts = TextToSpeech(requireContext()) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale.getDefault()
                adapter?.setTTS(tts!!)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tts?.stop()
        tts?.shutdown()
    }
} 