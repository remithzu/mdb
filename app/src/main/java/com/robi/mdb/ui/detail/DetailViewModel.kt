package com.robi.mdb.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robi.mdb.models.Actor
import com.robi.mdb.models.MovieDetail
import com.robi.mdb.networks.NetworkState
import com.robi.mdb.repository.MovieRepository
import kotlinx.coroutines.launch

class DetailViewModel(val repository: MovieRepository): ViewModel() {
    private val _movieDetail = MutableLiveData<NetworkState<MovieDetail>>()
    val movieDetail: LiveData<NetworkState<MovieDetail>> = _movieDetail


    private val _movieActor = MutableLiveData<NetworkState<Actor>>()
    val movieActor: LiveData<NetworkState<Actor>> = _movieActor

    private val _movieTrailer = MutableLiveData<NetworkState<String?>>()
    val movieTrailer: LiveData<NetworkState<String?>> = _movieTrailer

    fun getDetailMovie(movieId: Int) {
        _movieDetail.postValue(NetworkState.Loading())
        viewModelScope.launch {
            try {
                val response = repository.movieDetail(movieId)
                if (response.code()==200) {
                    response.body().let {
                        Log.e("VM", "response:: $it")
                        _movieDetail.postValue(NetworkState.Success(it!!))
                    }
                }
            } catch (t: Throwable) {
                _movieDetail.postValue(NetworkState.Error(t))
            }
        }
    }
    fun getActor(movieId: Int) {}
    fun getTrailer(movieId: Int) {}
}