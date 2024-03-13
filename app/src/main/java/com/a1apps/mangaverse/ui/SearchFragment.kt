package com.a1apps.mangaverse.ui

import com.a1apps.mangaverse.adapter.MangaAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.a1apps.mangaverse.R
import com.a1apps.mangaverse.databinding.FragmentSearchBinding
import com.a1apps.mangaverse.model.Manga
import com.a1apps.mangaverse.viewmodel.MangaViewModel
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Wave

class SearchFragment : Fragment(), MangaAdapter.OnMangaClickListener {

    private lateinit var binding: FragmentSearchBinding
    lateinit var mangaViewModel: MangaViewModel
    private lateinit var mangaAdapter: MangaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().findViewById<View>(R.id.bottomBar).visibility = View.VISIBLE

        mangaViewModel = (activity as MainActivity).mangaViewModel

        //Setting Up Recycler View
        mangaAdapter = MangaAdapter(this)
        binding.rVSearch.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rVSearch.adapter = mangaAdapter

        //Loading Animation
        val wave: Sprite = Wave()
        binding.spinKit.setIndeterminateDrawable(wave)


        binding.apply {
            searchButton.setOnClickListener {
                val query = searchEditText.text.toString().trim()
                if (query.isNotEmpty()) {

                    binding.spinKit.visibility = View.VISIBLE

                    mangaViewModel.searchManga(query)
                } else {
                    // Show a message to the user indicating that the search query is empty
                    Toast.makeText(
                        requireContext(),
                        "Search Field Cannot be Empty",
                        Toast.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }
            }
        }

        mangaViewModel.searchResults.observe(viewLifecycleOwner) { searchResponse ->

            binding.spinKit.visibility = View.GONE

            if (searchResponse.code != 200) {
                Toast.makeText(requireContext(), "No Matching Mangas", Toast.LENGTH_LONG).show()
            }
            mangaAdapter.submitList(searchResponse.data)

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