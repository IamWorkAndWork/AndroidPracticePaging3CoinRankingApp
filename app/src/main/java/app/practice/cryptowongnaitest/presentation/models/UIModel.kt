package app.practice.cryptowongnaitest.domain.models

sealed class UiModel {

    abstract var id: String

    data class CryptoDefaultItem(override var id: String, val crypto: CryptoModel) : UiModel()
    data class CryptoTitleItem(override var id: String, val crypto: CryptoModel) : UiModel()
    data class SeparatorItem(override var id: String) : UiModel()
    data class EndOfDataItem(override var id: String) : UiModel()
}