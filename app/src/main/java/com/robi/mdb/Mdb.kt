package com.robi.mdb

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.robi.mdb.di.AppModule
import com.robi.mdb.di.DaoModule
import com.robi.mdb.di.RepositoryModule
import com.robi.mdb.di.SharedModule
import com.robi.mdb.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Mdb: Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this
        context = applicationContext

        startKoin {
            androidLogger()
            androidContext(this@Mdb)
            modules(
                listOf(
                    AppModule,
                    SharedModule,
                    DaoModule,
                    RepositoryModule,
                    ViewModelModule
                )
            )
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: Mdb private set

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context private set
    }
}