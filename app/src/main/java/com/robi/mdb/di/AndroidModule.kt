package com.robi.mdb.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.robi.mdb.R
import com.robi.mdb.data.AppDatabase
import com.robi.mdb.networks.ApiProvider
import com.robi.mdb.networks.MovieApi
import com.robi.mdb.repository.MovieRepository
import com.robi.mdb.repository.MovieRepositoryImpl
import com.robi.mdb.ui.detail.DetailViewModel
import com.robi.mdb.utils.SharedPreferencesHelper
import org.koin.android.compat.ScopeCompat.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.get
import org.koin.dsl.module

val AppModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "mdb"
        ).allowMainThreadQueries().build()
    }
}

val ApiModule = module {
    single { ApiProvider }
}

val SharedModule = module {
    single<SharedPreferences> { androidContext().getSharedPreferences(androidContext().getString(R.string.app_name), Context.MODE_PRIVATE) }
    single { SharedPreferencesHelper(get()) }
}

val DaoModule = module {
    single { get<AppDatabase>().BookMarkDao() }
    single { get<AppDatabase>().GenreIdsDao() }
}

val RepositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl() }
}

val ViewModelModule = module {
    viewModel { DetailViewModel(get()) }
}