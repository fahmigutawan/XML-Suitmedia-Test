package com.example.suitmediatest.presentation.third_page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmediatest.R
import com.example.suitmediatest.data.util.DataHandler
import com.example.suitmediatest.databinding.ThirdScreenBinding
import com.example.suitmediatest.presentation.third_page.rv.UserRvAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ThirdScreen : Fragment(R.layout.third_screen) {
    lateinit var binding:ThirdScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ThirdScreenBinding.bind(view)
        val progressBar = binding.thirdscreenLoadingBar
        val viewModel by viewModels<ThirdScreenViewModel>()
        val rv = binding.thirdscreenRv


        viewModel.listState.observe(viewLifecycleOwner){
            when(it){
                is DataHandler.ERROR -> {
                    progressBar.visibility = View.GONE
                }
                is DataHandler.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
                is DataHandler.SUCCESS -> {
                    progressBar.visibility = View.GONE
                }
            }
        }

        viewModel.listData.observe(viewLifecycleOwner){
            val adapter = UserRvAdapter(it)
            val llm = LinearLayoutManager(activity)
            llm.orientation = LinearLayoutManager.VERTICAL
            rv.layoutManager = llm
            rv.adapter = adapter
        }

//        viewModel.listData.observe(viewLifecycleOwner){
//            adapter.updateUsers(it)
//        }

        viewModel.getList(1)
    }
}