package app.practice.cryptowongnaitest.di

import app.practice.cryptowongnaitest.data.repository.CryptoPagingRepository
import app.practice.cryptowongnaitest.data.repository.CryptoPagingRepositoryImpl
import app.practice.cryptowongnaitest.data.repository.CryptoRepository
import app.practice.cryptowongnaitest.data.repository.CryptoRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<CryptoPagingRepository> {
        CryptoPagingRepositoryImpl(
            cryptoToModelMapper = get(),
            cryptoDataSource = get()
        )
    }

    single<CryptoRepository> {
        CryptoRepositoryImpl(
            cryptoDataSource = get()
        )
    }

}