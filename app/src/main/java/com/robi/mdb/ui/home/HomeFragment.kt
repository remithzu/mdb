package com.robi.mdb.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.robi.mdb.databinding.FragmentHomeBinding
import com.robi.mdb.networks.NetworkState
import com.robi.mdb.ui.detail.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: Fragment() {
    val viewModel by viewModel<DetailViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.movieDetail.observe(viewLifecycleOwner) { movieDetailState ->
            when(movieDetailState) {
                is NetworkState.Loading -> {
                    Log.e("Fragment", "NetworkState:: Loading")
                }
                is NetworkState.Success -> {
                    Log.e("Fragment", "NetworkState:: Success:: ${movieDetailState.data}")
                }
                else -> {
                    Log.e("Fragment", "NetworkState:: Error:: ${movieDetailState.message?.message}")
                }
            }
        }.also {
            viewModel.getDetailMovie(1212073)
        }
    }
}