package app.practice.cryptowongnaitest.domain.models

sealed class UiModel {
    data class CryptoDefaultItem(val crypto: CryptoModel) : UiModel()
    data class CryptoTitleItem(val crypto: CryptoModel) : UiModel()
    data class SeparatorItem(val id: String) : UiModel()
    data class EndOfDataItem(val id: String) : UiModel()
}