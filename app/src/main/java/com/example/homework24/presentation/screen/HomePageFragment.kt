package com.example.homework24.presentation.screen

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.homework24.databinding.FragmentHomePageBinding
import com.example.homework24.presentation.base.BaseFragment
import com.example.homework24.presentation.extensions.showSnackBar
import com.example.homework24.presentation.screen.adapter.HomePageRecyclerAdapter
import com.example.homework24.presentation.screen.adapter.StoryRecyclerAdapter
import com.example.homework24.presentation.state.HomePageState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomePageFragment : BaseFragment<FragmentHomePageBinding>(FragmentHomePageBinding::inflate) {

    private val viewModel:HomePageViewModel by viewModels()
    lateinit var myAdapter: HomePageRecyclerAdapter

    override fun bind() {
        myAdapter = HomePageRecyclerAdapter()
        binding.recyclerView.adapter = myAdapter
    }

    override fun bindObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiStateFlow.collect(){
                    handleResult(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.oneTimeEventFlow.collect(){
                    showErrorMessage(it)
                }
            }
        }

    }

    private fun handleResult(state: HomePageState){

        showLoader(state.isLoading)

        state.isSuccess?.let {
            myAdapter.submitList(it)
        }
    }

    private fun showErrorMessage(errorMessage:String){
        binding.root.showSnackBar(errorMessage)
    }

    private fun showLoader(loading:Boolean){
        binding.progressBar.visibility = if(loading) View.VISIBLE else View.GONE
    }
}