package app.practice.cryptowongnaitest.presentation.models

sealed class UiAction {
    data class Search(val searchQuery: String) : UiAction()
}