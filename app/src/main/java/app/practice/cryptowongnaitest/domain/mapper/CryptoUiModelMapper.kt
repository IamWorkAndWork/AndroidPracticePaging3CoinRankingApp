package app.practice.cryptowongnaitest.domain.mapper

import app.practice.cryptowongnaitest.domain.models.CryptoModel
import app.practice.cryptowongnaitest.domain.models.UiModel

class CryptoUiModelMapper {

    fun toUiModel(cryptoModel: CryptoModel, index: Int): UiModel {
        val mod = index % 5
        return when (mod == 0) {
            true -> UiModel.CryptoTitleItem(cryptoModel.id, cryptoModel)
            false -> UiModel.CryptoDefaultItem(cryptoModel.id, cryptoModel)
        }
    }

}