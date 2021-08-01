package app.practice.cryptowongnaitest.domain.usecase

import app.practice.cryptowongnaitest.data.mapper.CryptoToModelMapper
import app.practice.cryptowongnaitest.data.repository.CryptoRepository
import app.practice.cryptowongnaitest.domain.models.CryptoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

interface GetSearchCryptoDataUseCase {
    fun execute(searchQuery: String): Flow<List<CryptoModel>?>
}

class GetSearchCryptoDataUseCaseImpl(
    private val cryptoRepository: CryptoRepository,
    private val cryptoToModelMapper: CryptoToModelMapper
) : GetSearchCryptoDataUseCase {

    override fun execute(searchQuery: String): Flow<List<CryptoModel>?> {

        return cryptoRepository.getSearchCryptoList(searchQuery = searchQuery)
            .map { cryptoList ->
                cryptoToModelMapper.toCryptoModelList(cryptoList)
                    ?.filter {
                        it.id != "null"
                    }?.distinctBy {
                        it.id
                    }
            }
    }

}