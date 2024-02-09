package com.robi.mdb.repository

interface MovieRepository {
    suspend fun movieDetail()
    suspend fun movieActor()
    suspend fun movieTrailer()
}