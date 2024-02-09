package com.robi.mdb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robi.mdb.models.Actor
import com.robi.mdb.models.MovieDetail
import com.robi.mdb.networks.NetworkState
import com.robi.mdb.repository.MovieRepository

class DetailViewModel(val repository: MovieRepository): ViewModel() {
    private val _movieDetailRepository: MutableLiveData<NetworkState<MovieDetail>>
        get() = MutableLiveData<com.robi.mdb.networks.NetworkState<MovieDetail>>()
    val movieDetailRepository: LiveData<NetworkState<MovieDetail>> = _movieDetailRepository

    private val _movieActorRepository = MutableLiveData<NetworkState<Actor>>()
    val movieActorRepository: LiveData<NetworkState<Actor>> = _movieActorRepository

    private val _movieTrailerRepository = MutableLiveData<NetworkState<String?>>()
    val movieTrailerRepository: LiveData<NetworkState<String?>> = _movieTrailerRepository

    fun getDetailMovie(movieId: Int) {}
    fun getActor(movieId: Int) {}
    fun getTrailer(movieId: Int) {}
}