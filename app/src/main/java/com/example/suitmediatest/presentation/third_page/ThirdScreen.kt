package com.example.suitmediatest.presentation.third_page

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.suitmediatest.R
import com.example.suitmediatest.data.util.DataHandler
import com.example.suitmediatest.databinding.ThirdScreenBinding
import com.example.suitmediatest.presentation.third_page.rv.UserRvAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ThirdScreen : Fragment(R.layout.third_screen) {
    lateinit var binding: ThirdScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ThirdScreenBinding.bind(view)
        binding.thirdscreenAppbar.appbarTitle.setText("Third Screen")
        val progressBar = binding.thirdscreenLoadingBar
        val viewModel by viewModels<ThirdScreenViewModel>()
        val rv = binding.thirdscreenRv
        val swipeRefresh = binding.thirdscreenSwipeLayout

        binding.thirdscreenAppbar.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.listState.observe(viewLifecycleOwner) {
            swipeRefresh.isRefreshing = false
            when (it) {
                is DataHandler.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(activity, "Something went wrong, try again later", Toast.LENGTH_SHORT).show()
                }

                is DataHandler.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }

                is DataHandler.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    if(viewModel.endOfPagination.value == true){
                        Toast.makeText(activity, "End of the list", Toast.LENGTH_SHORT).show()
                    }
                }

                is DataHandler.EMPTY -> {
//                    Toast.makeText(activity, "End of the list", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.listData.observe(viewLifecycleOwner) {
            activity?.let { activity ->
                val adapter = UserRvAdapter(activity, it)
                val llm = LinearLayoutManager(activity)
                llm.orientation = LinearLayoutManager.VERTICAL
                rv.layoutManager = llm
                rv.adapter = adapter
            }
        }

        swipeRefresh.setOnRefreshListener {
            viewModel.endOfPagination.postValue(false)
            viewModel.listData.postValue(emptyList())
            viewModel.pageNow.postValue(1)
            viewModel.getList(1)
        }

        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (
                    !recyclerView.canScrollVertically(1) &&
                    newState == RecyclerView.SCROLL_STATE_IDLE &&
                    viewModel.listState.value is DataHandler.SUCCESS
                ) {
                    if(viewModel.endOfPagination.value == false) {
                        viewModel.pageNow.postValue((viewModel.pageNow.value ?: 0) + 1)
                    }
                }
            }
        })

//        viewModel.listData.observe(viewLifecycleOwner){
//            adapter.updateUsers(it)
//        }

        viewModel.pageNow.observe(viewLifecycleOwner) {
            if (viewModel.listState.value is DataHandler.SUCCESS) {
                viewModel.getList(it)
            }
        }
    }
}