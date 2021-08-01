package app.practice.cryptowongnaitest.presentation

import app.practice.cryptowongnaitest.domain.models.UiModel
import app.practice.cryptowongnaitest.presentation.epoxy.*
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import kotlinx.coroutines.ObsoleteCoroutinesApi

@OptIn(ObsoleteCoroutinesApi::class)
class CryptoEpoxyController() : PagingDataEpoxyController<UiModel>() {

    override fun buildItemModel(currentPosition: Int, item: UiModel?): EpoxyModel<*> {
        return item?.let { uiModel ->
            when (uiModel) {
                is UiModel.CryptoDefaultItem -> {
                    CryptoItemDefaultEpoxyModel_().id(uiModel.crypto.id).cryptoModel(uiModel.crypto)
                }
                is UiModel.CryptoTitleItem -> {
                    CryptoItemTitleEpoxyModel_().id(uiModel.crypto.id).cryptoModel(uiModel.crypto)
                }
                is UiModel.SeparatorItem -> {
                    SeperatorItemEpoxyModel_().id(currentPosition)
                }
                else -> {
                    SeperatorItemEpoxyModel_().id(currentPosition)
                }
            }
        } ?: kotlin.run {
            SeperatorItemEpoxyModel_().id(currentPosition)
        }
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        super.addModels(models)

        LoadingItemEpoxyModel_().id("LOADING")
            .addIf(models.isNotEmpty() , this)

    }

}