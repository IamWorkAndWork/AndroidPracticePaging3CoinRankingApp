package app.practice.cryptowongnaitest.di

import app.practice.cryptowongnaitest.data.mapper.CryptoToModelMapper
import app.practice.cryptowongnaitest.domain.mapper.CryptoUiModelMapper
import org.koin.dsl.module

val mapperModule = module {

    single<CryptoToModelMapper> {
        CryptoToModelMapper()
    }

    single<CryptoUiModelMapper> {
        CryptoUiModelMapper()
    }

}