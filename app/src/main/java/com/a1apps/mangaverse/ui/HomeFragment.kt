package com.a1apps.mangaverse.ui

import com.a1apps.mangaverse.adapter.MangaAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.a1apps.mangaverse.R
import com.a1apps.mangaverse.databinding.FragmentHomeBinding
import com.a1apps.mangaverse.model.Manga
import com.a1apps.mangaverse.viewmodel.MangaViewModel

class HomeFragment : Fragment(), MangaAdapter.OnMangaClickListener {

    private lateinit var binding: FragmentHomeBinding
    lateinit var mangaViewModel: MangaViewModel
    private lateinit var mangaAdapter: MangaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().findViewById<View>(R.id.bottomBar).visibility = View.VISIBLE

        mangaViewModel = (activity as MainActivity).mangaViewModel

        //Setting Up Recycler View
        mangaAdapter = MangaAdapter(this)
        binding.rVMangas.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rVMangas.adapter = mangaAdapter

        mangaViewModel.mangas.observe(viewLifecycleOwner) {

            Log.d("A1apps", it.code.toString())
            mangaAdapter.submitList(it.data)
        }
    }

    override fun onMangaClick(manga: Manga) {

        val bundle = Bundle().apply {
            putSerializable("manga", manga)
        }
        val detailsFragment = DetailsFragment()
        detailsFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailsFragment)
            .addToBackStack(null)
            .commit()
    }


}