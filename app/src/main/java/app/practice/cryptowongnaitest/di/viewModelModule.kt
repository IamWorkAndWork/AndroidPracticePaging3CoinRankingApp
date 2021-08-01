package app.practice.cryptowongnaitest.di

import androidx.lifecycle.SavedStateHandle
import app.practice.cryptowongnaitest.domain.usecase.GetSearchCryptoPagingDataUseCase
import app.practice.cryptowongnaitest.presentation.CryptoListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        CryptoListViewModel(
            getCryptoPagingDataUseCase = get(),
            getSearchCryptoPagingDataUseCase = get(),
            getSearchCryptoDataUseCase = get(),
            savedStateHandle = SavedStateHandle(),
        )
    }

}