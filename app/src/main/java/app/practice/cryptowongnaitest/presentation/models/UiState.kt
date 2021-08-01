package app.practice.cryptowongnaitest.presentation.models

import androidx.paging.PagingData
import app.practice.cryptowongnaitest.domain.models.UiModel
import app.practice.cryptowongnaitest.utils.DEFAULT_QUERY

data class UiState(
    val query: String = DEFAULT_QUERY,
    val lastQueryScrolled: String = DEFAULT_QUERY,
    val pagingData: PagingData<UiModel> = PagingData.empty()
)