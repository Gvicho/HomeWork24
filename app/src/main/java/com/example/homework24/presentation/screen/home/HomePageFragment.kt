package com.example.homework24.presentation.screen.home

import android.util.Log.d
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homework24.R
import com.example.homework24.databinding.FragmentHomePageBinding
import com.example.homework24.presentation.base.BaseFragment
import com.example.homework24.presentation.event.HomePageEvents
import com.example.homework24.presentation.extensions.safeNavigateWithArguments
import com.example.homework24.presentation.extensions.showSnackBar
import com.example.homework24.presentation.screen.adapter.ClickCallBack
import com.example.homework24.presentation.screen.adapter.HomePageRecyclerAdapter
import com.example.homework24.presentation.state.HomePageState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomePageFragment : BaseFragment<FragmentHomePageBinding>(FragmentHomePageBinding::inflate),ClickCallBack {

    private val viewModel: HomePageViewModel by viewModels()
    private lateinit var myAdapter: HomePageRecyclerAdapter

    override fun bind() {
        d("tag123", "fragment onCreate")
        myAdapter = HomePageRecyclerAdapter(this)
        binding.recyclerView.adapter = myAdapter

    }

    override fun bindObservers() {
        bindStateObservers()
        bindErrorObservers()
        bindNavigationObservers()
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
    private fun bindNavigationObservers(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.navigationEventFlow.collect(){
                    handleNavigationEvent(it)
                }
            }
        }
    }

    private fun handleNavigationEvent(navEvent:HomePageNavigationEvent){
        when(navEvent){
            is HomePageNavigationEvent.NavigateToDetails -> navigateToDetailsPage(navEvent.id)
        }
    }

    private fun navigateToDetailsPage(id:Int){
        val bundle = bundleOf("id" to id)
        findNavController().safeNavigateWithArguments(R.id.action_homePageFragment_to_detailsFragment,bundle)
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

    override fun postClicked(id: Int) {
        viewModel.onEvent(HomePageEvents.OpenDetailsPage(id))
    }
}