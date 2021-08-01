package app.practice.cryptowongnaitest

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import app.practice.cryptowongnaitest.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@Application)
            androidLogger(Level.ERROR)
            modules(
                listOf(
                    dataSourceModule,
                    mapperModule,
                    networkModule,
                    repositoryModule,
                    usecaseModule,
                    viewModelModule
                )
            )
        }
    }
}