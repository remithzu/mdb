package com.robi.mdb.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.robi.mdb.R
import com.robi.mdb.data.AppDatabase
import com.robi.mdb.utils.SharedPreferencesHelper
import org.koin.android.ext.koin.androidContext
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

val SharedModule = module {
    single<SharedPreferences> { androidContext().getSharedPreferences(androidContext().getString(R.string.app_name), Context.MODE_PRIVATE) }
    single { SharedPreferencesHelper(get()) }
}

val DaoModule = module {
    /*single { get<AppDatabase>().UserDao() }
    single { get<AppDatabase>().ManhwaDao() }
    single { get<AppDatabase>().PopularDao() }
    single { get<AppDatabase>().RecommendDao() }
    single { get<AppDatabase>().HistoryDao() }
    single { get<AppDatabase>().ChaptersDao() }*/
}

val RepositoryModule = module {
    /*single<UserRepository> { UserRepositoryImpl(get()) }
    single<ManhwaRepository> { ManhwaRepositoryImpl(get()) }*/
}

val ViewModelModule = module {
    /*viewModel { UserViewModel(get()) }
    viewModel { ManhwaViewModel(get()) }*/
}