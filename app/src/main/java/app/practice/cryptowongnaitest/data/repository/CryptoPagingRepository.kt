package app.practice.cryptowongnaitest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import app.practice.cryptowongnaitest.data.datasource.CryptoDataSource
import app.practice.cryptowongnaitest.data.datasource.CryptoPagingSource
import app.practice.cryptowongnaitest.data.datasource.CryptoSearchPagingSource
import app.practice.cryptowongnaitest.data.mapper.CryptoToModelMapper
import app.practice.cryptowongnaitest.domain.models.CryptoModel
import kotlinx.coroutines.flow.Flow

interface CryptoPagingRepository {
    fun getCryptoResultStream(): Flow<PagingData<CryptoModel>>
    fun getSearchCryptoResultStream(cryptoSearchList: List<CryptoModel>?): Flow<PagingData<CryptoModel>>
}

class CryptoPagingRepositoryImpl(
    private val cryptoDataSource: CryptoDataSource,
    private val cryptoToModelMapper: CryptoToModelMapper
) : CryptoPagingRepository {

    companion object {
        const val NETWORK_LIMIT = 10
    }

    override fun getCryptoResultStream(): Flow<PagingData<CryptoModel>> {
        return Pager(
            config = getPagingConfig(),
            pagingSourceFactory = {
                CryptoPagingSource(
                    cryptoDataSource = cryptoDataSource,
                    cryptoToModelMapper = cryptoToModelMapper
                )
            }
        ).flow
    }

    override fun getSearchCryptoResultStream(cryptoSearchList: List<CryptoModel>?): Flow<PagingData<CryptoModel>> {

        return Pager(
            config = getPagingConfig(),
            pagingSourceFactory = {
                CryptoSearchPagingSource(
                    cryptoSearchList = cryptoSearchList,
                )
            }
        ).flow
    }

    private fun getPagingConfig(): PagingConfig {
        val pagingConfig = PagingConfig(
            initialLoadSize = NETWORK_LIMIT,
            pageSize = NETWORK_LIMIT,
            enablePlaceholders = false,
            prefetchDistance = 1
        )
        return pagingConfig
    }
}
