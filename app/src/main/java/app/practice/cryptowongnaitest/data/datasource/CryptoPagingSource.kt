package app.practice.cryptowongnaitest.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.practice.cryptowongnaitest.data.mapper.CryptoToModelMapper
import app.practice.cryptowongnaitest.domain.models.CryptoModel
import retrofit2.HttpException
import java.io.IOException

class CryptoPagingSource(
    private val cryptoDataSource: CryptoDataSource,
    private val cryptoToModelMapper: CryptoToModelMapper
) : PagingSource<Int, CryptoModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CryptoModel> {

        return try {

            val offset = params.key ?: 0

            val cryptoList = cryptoDataSource.searchCoins(offset = offset, limit = params.loadSize)
            val mapResponse = cryptoToModelMapper.toCryptoModelList(cryptoList)

            val response = if (!mapResponse.isNullOrEmpty()) mapResponse else emptyList()

            val nextKey = if (response.isEmpty()) {
                null
            } else {
                offset + params.loadSize
            }

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextKey
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, CryptoModel>): Int? {
        return null
    }

}