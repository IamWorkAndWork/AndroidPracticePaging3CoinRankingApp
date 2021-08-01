package app.practice.cryptowongnaitest.data.repository

import app.practice.cryptowongnaitest.data.datasource.CryptoDataSource
import com.example.data.model.response.Crypto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface CryptoRepository {
    fun getSearchCryptoList(searchQuery: String): Flow<Result<List<Crypto>>>
}

class CryptoRepositoryImpl(
    private val cryptoDataSource: CryptoDataSource
) : CryptoRepository {

    override fun getSearchCryptoList(searchQuery: String) = flow {
        val cryptoList = mutableListOf<Crypto>()
        getResponse(cryptoDataSource.searchCoinsByIds(ids = searchQuery))?.let { _cryptoList ->
            cryptoList.addAll(_cryptoList)
        }
        getResponse(cryptoDataSource.searchCoinsByPrefix(prefix = searchQuery))?.let { _cryptoList ->
            cryptoList.addAll(_cryptoList)
        }
        getResponse(cryptoDataSource.searchCoinsBySlugs(slugs = searchQuery))?.let { _cryptoList ->
            cryptoList.addAll(_cryptoList)
        }
        getResponse(cryptoDataSource.searchCoinsBySymbols(symbols = searchQuery))?.let { _cryptoList ->
            cryptoList.addAll(_cryptoList)
        }
        val result = Result.success(cryptoList)
        emit(result)
    }

    private fun getResponse(searchCoinsByIds: Result<List<Crypto>?>): List<Crypto>? {
        return if (searchCoinsByIds.isSuccess) {
            searchCoinsByIds.getOrNull()
        } else {
            emptyList()
        }
    }


}