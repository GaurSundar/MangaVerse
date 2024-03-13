package com.a1apps.mangaverse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.a1apps.mangaverse.R
import com.a1apps.mangaverse.databinding.MangaItemBinding
import com.a1apps.mangaverse.model.Manga
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MangaAdapter(private val listener: OnMangaClickListener) :
    ListAdapter<Manga, MangaAdapter.MangaViewHolder>(
        MangaDiffCallback()
    ) {

    private class MangaDiffCallback : DiffUtil.ItemCallback<Manga>() {
        override fun areItemsTheSame(oldItem: Manga, newItem: Manga): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Manga, newItem: Manga): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val binding = MangaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MangaViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        val manga = getItem(position)
        holder.bind(manga)
    }

    inner class MangaViewHolder(
        private val binding: MangaItemBinding,
        private val listener: OnMangaClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(manga: Manga) {
            binding.apply {
                // Load manga title
                mangaTitle.text = manga.title

                // For Marquee Text
                mangaTitle.isSelected = true

                // Load manga Poster using Glide
                Glide.with(itemView)
                    .load(manga.thumb)
                    .apply(RequestOptions().placeholder(R.drawable.placeholder_manga))
                    .into(mangaPoster)

                mangaType.text = manga.type.uppercase()

                root.setOnClickListener {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        listener.onMangaClick(manga)
                    }
                }
            }
        }
    }

    interface OnMangaClickListener {
        fun onMangaClick(manga: Manga)
    }


}
