package app.practice.cryptowongnaitest.di

import app.practice.cryptowongnaitest.data.datasource.CryptoDataSource
import app.practice.cryptowongnaitest.data.datasource.CryptoDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {

    single<CryptoDataSource> {
        CryptoDataSourceImpl(
            apiService = get()
        )
    }

}