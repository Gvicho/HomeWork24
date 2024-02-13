package com.example.homework24.presentation.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework24.databinding.ItemStoryBinding
import com.example.homework24.presentation.model.StoryUI

class StoryRecyclerAdapter(
    private val data:List<StoryUI>
): RecyclerView.Adapter<StoryRecyclerAdapter.StoryViewHolder>() {

    inner class StoryViewHolder(private val binding: ItemStoryBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.apply {

                val storyUI = data[position]

                Glide.with(itemView.context)
                    .load(storyUI.cover)
                    .into(image)

                tvTitle.text = storyUI.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        return StoryViewHolder(ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(position)
    }

}