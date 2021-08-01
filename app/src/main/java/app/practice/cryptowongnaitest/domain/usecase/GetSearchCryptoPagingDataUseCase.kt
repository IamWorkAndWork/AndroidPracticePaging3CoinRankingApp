package app.practice.cryptowongnaitest.domain.usecase

import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import app.practice.cryptowongnaitest.data.repository.CryptoPagingRepository
import app.practice.cryptowongnaitest.domain.mapper.CryptoUiModelMapper
import app.practice.cryptowongnaitest.domain.models.CryptoModel
import app.practice.cryptowongnaitest.domain.models.UiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface GetSearchCryptoPagingDataUseCase {
    fun execute(cryptoSearchList: List<CryptoModel>?): Flow<PagingData<UiModel>>
}

class GetSearchCryptoPagingDataUseCaseImpl(
    private val cryptoPagingRepository: CryptoPagingRepository,
    private val cryptoUiModelMapper: CryptoUiModelMapper
) : GetSearchCryptoPagingDataUseCase {

    override fun execute(cryptoSearchList: List<CryptoModel>?): Flow<PagingData<UiModel>> {

        return cryptoPagingRepository.getSearchCryptoResultStream(cryptoSearchList = cryptoSearchList)
            .map { pagingData ->

                var index = 0

                pagingData.map { cryptoModel ->
                    index++
                    cryptoUiModelMapper.toUiModel(cryptoModel = cryptoModel, index = index)
                }.insertSeparators { before: UiModel?, after: UiModel? ->
                    return@insertSeparators when {
                        before == null -> UiModel.SeparatorItem("SEPERATOR")
                        after == null -> UiModel.EndOfDataItem("FOOTER")
                        else -> UiModel.SeparatorItem("BETWEEN ITEM")
                    }
                }
            }

    }

}