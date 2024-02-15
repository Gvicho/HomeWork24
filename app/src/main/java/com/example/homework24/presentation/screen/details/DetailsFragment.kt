package com.example.homework24.presentation.screen.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.homework24.databinding.FragmentDetailsBinding
import com.example.homework24.presentation.base.BaseFragment
import com.example.homework24.presentation.event.DetailsPageEvents
import com.example.homework24.presentation.extensions.convertToDateString
import com.example.homework24.presentation.extensions.loadImageWithGlide
import com.example.homework24.presentation.extensions.showSnackBar
import com.example.homework24.presentation.model.PostUI
import com.example.homework24.presentation.screen.adapter.ImagesRecyclerAdapter
import com.example.homework24.presentation.state.DetailsPageState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val viewModel:DetailsViewModel by viewModels()
    private lateinit var myAdapter:ImagesRecyclerAdapter

    private val args :DetailsFragmentArgs by navArgs()
    private var postId :Int? = null


    override fun initData(savedInstanceState: Bundle?) {
        postId = args.id
    }

    override fun bind() {
        viewModel.onEvent(DetailsPageEvents.LoadPage(postId ?: -1))
    }

    override fun bindObservers() {
        bindStateObservers()
        bindErrorObservers()
    }

    private fun bindStateObservers(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiStateFlow.collect(){
                    handleResult(it)
                }
            }
        }
    }
    private fun bindErrorObservers(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.oneTimeEventFlow.collect(){
                    showErrorMessage(it)
                }
            }
        }
    }

    private fun handleResult(state: DetailsPageState){

        showLoader(state.isLoading)

        state.isSuccess?.let {
            bindLoadedDataToPage(it)
        }

    }

    private fun bindLoadedDataToPage(post: PostUI){
        myAdapter = ImagesRecyclerAdapter(post.images)
        binding.imagesRecycler.adapter = myAdapter
        val owner = post.owner
        if(owner.profile.isNotEmpty())loadPostOwnerProfileImage(owner.profile)
        binding.apply {
            tvBodyTitle.text = post.title
            tvDate.text = owner.postDate.convertToDateString()
            tvName.text = owner.name
            tvLikesQuantity.text = post.likes.toString()
            tvSharesQuantity.text = post.shareContent
            tvCommentsQuantity.text = post.comments.toString()
        }
    }


    private fun loadPostOwnerProfileImage(uri:String){
        context?.loadImageWithGlide(uri,binding.postOwnerImage)
    }


    private fun showErrorMessage(errorMessage:String){
        binding.root.showSnackBar(errorMessage)
    }

    private fun showLoader(loading:Boolean){
        binding.progressBar.visibility = if(loading) View.VISIBLE else View.GONE
    }

}
