package com.a1apps.mangaverse.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a1apps.mangaverse.databinding.FragmentDetailsBinding
import com.a1apps.mangaverse.model.Manga
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Receive the Clicked Manga and Update the Manga Details
        arguments?.let { args ->
            val manga = args.getSerializable("manga") as Manga
            updateUI(manga)
        }
    }

    private fun updateUI(manga: Manga) {
        Glide.with(this)
            .load(manga.thumb)
            .apply(RequestOptions.centerCropTransform())
            .into(binding.imgManga)

        binding.collapsingToolbar.title = manga.title
        binding.mangaType.append(manga.type.uppercase())

        if (manga.sub_title.isEmpty()) {
            binding.tvSubTitle.visibility = View.GONE
            binding.mangaSubTitle.visibility = View.GONE
        }

        if (manga.total_chapter <= 0) {
            binding.mangaChapter.visibility = View.GONE
        }

        binding.mangaChapter.append(manga.total_chapter.toString())
        binding.mangaSubTitle.text = manga.sub_title
        binding.mangaStatus.text = manga.status.uppercase()
        binding.mangaSummary.text = manga.summary

    }

}