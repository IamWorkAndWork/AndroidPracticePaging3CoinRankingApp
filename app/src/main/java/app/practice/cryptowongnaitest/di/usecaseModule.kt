package app.practice.cryptowongnaitest.di

import app.practice.cryptowongnaitest.domain.usecase.*
import org.koin.dsl.module

val usecaseModule = module {

    single<GetCryptoPagingDataUseCase> {
        GetCryptoPagingDataUseCaseImpl(
            cryptoPagingRepository = get(),
            cryptoUiModelMapper = get()
        )
    }

    single<GetSearchCryptoPagingDataUseCase> {
        GetSearchCryptoPagingDataUseCaseImpl(
            cryptoPagingRepository = get(),
            cryptoUiModelMapper = get()
        )
    }

    single<GetSearchCryptoDataUseCase> {
        GetSearchCryptoDataUseCaseImpl(
            cryptoRepository = get(),
            cryptoToModelMapper = get()
        )
    }

}