package com.dailystore

import android.app.Application
import com.dailystore.repositories.AuthRepository
import com.dailystore.repositories.UsersRepository
import com.dailystore.viewmodels.auth.AuthViewModelFactory
import com.dailystore.viewmodels.home.HomeViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class DailyStoreApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@DailyStoreApplication))

        bind() from singleton { UsersRepository() }
        bind() from singleton { AuthRepository() }
        bind() from provider { AuthViewModelFactory(instance(), instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }
    }
}