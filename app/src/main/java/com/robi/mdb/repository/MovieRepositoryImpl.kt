package com.robi.mdb.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl: MovieRepository {
    override suspend fun movieDetail() {
        withContext(Dispatchers.IO) {

        }
    }

    override suspend fun movieActor() {
        withContext(Dispatchers.IO) {

        }
    }

    override suspend fun movieTrailer() {
        withContext(Dispatchers.IO) {

        }
    }
}