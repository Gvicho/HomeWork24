package com.example.homework24.presentation.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework24.databinding.ItemPostBinding
import com.example.homework24.databinding.ItemStoryRecyclerBinding
import com.example.homework24.presentation.extensions.convertToDateString
import com.example.homework24.presentation.extensions.loadImageWithGlide
import com.example.homework24.presentation.model.HomePageItemUI
import com.example.homework24.presentation.model.ItemType

class HomePageRecyclerAdapter(
    private val listener:ClickCallBack
) : ListAdapter<HomePageItemUI, RecyclerView.ViewHolder>(
    DIFF_CALLBACK
) {
    lateinit var myAdapter: StoryRecyclerAdapter

    companion object {

        const val Item_Type_Recycler = 1
        const val Item_Type_Post = 2



        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HomePageItemUI>() {
            override fun areItemsTheSame(oldItem: HomePageItemUI, newItem: HomePageItemUI): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: HomePageItemUI, newItem: HomePageItemUI): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class PostItemViewHolder(private val binding: ItemPostBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){

            val postObject = currentList[position].post


            postObject?.let {post->

                val owner = post.owner

                loadImages(post.images)
                if(owner.profile.isNotEmpty())loadPostOwnerProfileImage(owner.profile)
                binding.apply {
                    tvBodyTitle.text = post.title
                    tvDate.text = owner.postDate.convertToDateString()
                    tvName.text = owner.name
                    tvLikesQuantity.text = post.likes.toString()
                    tvSharesQuantity.text = post.shareContent
                    tvCommentsQuantity.text = post.comments.toString()

                    root.setOnClickListener{
                        listener.postClicked(post.id)
                    }
                }

            }

        }

        /*
        private fun loadUserProfileImage(profileImage:String){
            Glide.with(itemView.context)
                .load(profileImage)
                .into(binding.userImage)
        }*/

        private fun loadPostOwnerProfileImage(profileImage:String){
            itemView.context.loadImageWithGlide(profileImage,binding.postOwnerImage)
        }

        private fun loadImages(list:List<String>){
            binding.apply {

                if(list.size >= 1){
                    image1.visibility = View.VISIBLE
                    itemView.context.loadImageWithGlide(list[0],image1)
                }
                if(list.size >= 2){
                    image2.visibility = View.VISIBLE
                    itemView.context.loadImageWithGlide(list[1],image2)
                }
                if(list.size >= 3){
                    image3.visibility = View.VISIBLE
                    itemView.context.loadImageWithGlide(list[2],image3)
                }
            }
        }

    }

    inner class StoryRecyclerViewHolder(private val binding: ItemStoryRecyclerBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(position: Int){
            val professionsList = currentList[position].storyList?: emptyList()
            myAdapter = StoryRecyclerAdapter(professionsList)
            binding.root.adapter = myAdapter
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).itemType) {
            ItemType.STORY_RECYCLER ->{
                Item_Type_Recycler
            }
            ItemType.POST ->{
                Item_Type_Post
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Item_Type_Post -> PostItemViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            Item_Type_Recycler -> StoryRecyclerViewHolder(ItemStoryRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw IllegalStateException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is PostItemViewHolder) holder.bind(position)
        else if(holder is StoryRecyclerViewHolder)holder.bind(position)
    }
}

interface ClickCallBack{
    fun postClicked(id:Int)
}