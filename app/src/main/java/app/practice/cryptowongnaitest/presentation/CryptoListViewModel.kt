package app.practice.cryptowongnaitest.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import app.practice.cryptowongnaitest.domain.models.UiModel
import app.practice.cryptowongnaitest.domain.usecase.GetCryptoPagingDataUseCase
import app.practice.cryptowongnaitest.domain.usecase.GetSearchCryptoDataUseCase
import app.practice.cryptowongnaitest.domain.usecase.GetSearchCryptoPagingDataUseCase
import app.practice.cryptowongnaitest.utils.DEFAULT_QUERY
import app.practice.cryptowongnaitest.utils.LAST_SEARCH_QUERY
import app.practice.cryptowongnaitest.presentation.base.BaseViewModel
import app.practice.cryptowongnaitest.presentation.models.UiAction
import app.practice.cryptowongnaitest.presentation.models.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


@OptIn(ExperimentalCoroutinesApi::class)
class CryptoListViewModel(
    private val getCryptoPagingDataUseCase: GetCryptoPagingDataUseCase,
    private val getSearchCryptoPagingDataUseCase: GetSearchCryptoPagingDataUseCase,
    private val getSearchCryptoDataUseCase: GetSearchCryptoDataUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    var networkStatus = false

    val state: StateFlow<UiState>

    val accept: (UiAction) -> Unit

    init {
        val initialQuery: String = savedStateHandle.get(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY

        val actionStateFlow = MutableSharedFlow<UiAction>()
        val searches = actionStateFlow
            .filterIsInstance<UiAction.Search>()
            .distinctUntilChanged()
            .onStart {
                emit(
                    UiAction.Search(searchQuery = initialQuery)
                )
            }

        state = searches.flatMapLatest { searchQuery ->
            searchRepo(searchQuery = searchQuery.searchQuery)
                .distinctUntilChangedBy { it }
                .map { pagingData ->
                    UiState(
                        query = searchQuery.searchQuery,
                        pagingData = pagingData
                    )
                }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = UiState()
        )

        accept = { action ->
            viewModelScope.launch {
                actionStateFlow.emit(action)
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun searchRepo(searchQuery: String): Flow<PagingData<UiModel>> {
        if (searchQuery.isEmpty()) {

            return getCryptoPagingDataUseCase.execute()
                .cachedIn(viewModelScope)

        } else {

            return getSearchCryptoDataUseCase.execute(searchQuery = searchQuery)
                .flatMapMerge { cryptoSearchList ->
                    getSearchCryptoPagingDataUseCase.execute(cryptoSearchList = cryptoSearchList)
                }
                .cachedIn(viewModelScope)
        }
    }

    override fun showError(message: String) {

    }

    override fun onCleared() {
        savedStateHandle[LAST_SEARCH_QUERY] = state.value.query
        super.onCleared()
    }

}

