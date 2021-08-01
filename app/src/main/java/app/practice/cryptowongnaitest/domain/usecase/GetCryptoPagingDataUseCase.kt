package app.practice.cryptowongnaitest.domain.usecase

import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import app.practice.cryptowongnaitest.data.repository.CryptoPagingRepository
import app.practice.cryptowongnaitest.domain.mapper.CryptoUiModelMapper
import app.practice.cryptowongnaitest.domain.models.UiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface GetCryptoPagingDataUseCase {
    fun execute(): Flow<PagingData<UiModel>>
}

class GetCryptoPagingDataUseCaseImpl(
    private val cryptoPagingRepository: CryptoPagingRepository,
    private val cryptoUiModelMapper: CryptoUiModelMapper
) : GetCryptoPagingDataUseCase {

    override fun execute(): Flow<PagingData<UiModel>> {

        return cryptoPagingRepository.getCryptoResultStream()
            .map { pagingData ->

                var index = 0

                pagingData.map { cryptoModel ->
                    index++
                    cryptoUiModelMapper.toUiModel(cryptoModel = cryptoModel, index = index)
                }.insertSeparators { before: UiModel?, after: UiModel? ->
                    return@insertSeparators when {
                        before == null -> UiModel.SeparatorItem("HEADER")
                        after == null -> UiModel.SeparatorItem("FOOTER")
                        else -> UiModel.SeparatorItem("BETWEEN ITEM")
                    }
                }

            }

    }

}