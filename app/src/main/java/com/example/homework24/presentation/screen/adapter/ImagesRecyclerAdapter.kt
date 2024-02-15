package com.example.homework24.presentation.screen.adapter

import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework24.databinding.ItemImageBinding
import com.example.homework24.presentation.extensions.loadImageWithGlide

class ImagesRecyclerAdapter(
    private val data:List<String>
): RecyclerView.Adapter<ImagesRecyclerAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(private val binding: ItemImageBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.apply {
                itemView.context.loadImageWithGlide(data[position],binding.root)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        d("tag123","$data")
        return ImageViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(position)
    }

}