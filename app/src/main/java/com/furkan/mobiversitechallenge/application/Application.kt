package com.furkan.mobiversitechallenge.application

import android.app.Application
import com.furkan.mobiversitechallenge.data.local.LocalDataSourceModule
import com.furkan.mobiversitechallenge.data.remote.remoteDataSourceModule
import com.furkan.mobiversitechallenge.data.repostory.RepositoryModule
import com.furkan.mobiversitechallenge.di.local_db.roomDBModule
import com.furkan.mobiversitechallenge.di.networking.networkModule
import com.furkan.mobiversitechallenge.ui.fragment.search.fragmentModule
import com.furkan.mobiversitechallenge.ui.fragment.search.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@Application)
            modules(listOf(roomDBModule,LocalDataSourceModule,RepositoryModule,viewModelModule,fragmentModule,networkModule,remoteDataSourceModule))
        }
    }
}