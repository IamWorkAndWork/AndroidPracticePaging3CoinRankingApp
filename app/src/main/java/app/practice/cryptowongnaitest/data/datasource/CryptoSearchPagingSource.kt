package app.practice.cryptowongnaitest.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.practice.cryptowongnaitest.data.mapper.CryptoToModelMapper
import app.practice.cryptowongnaitest.domain.models.CryptoModel
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

class CryptoSearchPagingSource(
    private val cryptoSearchList: List<CryptoModel>?,
) : PagingSource<Int, CryptoModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CryptoModel> {

        return try {

            val offset = params.key ?: 0

            val tempSearchResult = if (!cryptoSearchList.isNullOrEmpty()) {
                cryptoSearchList
            } else {
                emptyList()
            }

            val totalSize = tempSearchResult.size

            val nextKey = getNextKey(tempSearchResult, offset, params.loadSize, totalSize)

            val limit = getLimit(nextKey, totalSize)

            delay(300L)

            val response = cryptoSearchList?.subList(offset, limit)

            LoadResult.Page(
                data = response ?: emptyList(),
                prevKey = null,
                nextKey = nextKey
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

    private fun getLimit(
        nextKey: Int?,
        totalSize: Int
    ) = if (nextKey != null) {
        nextKey
    } else {
        totalSize
    }

    private fun getNextKey(
        tempSearchResult: List<CryptoModel>,
        offset: Int,
        loadSize: Int,
        totalSize: Int
    ) = if (tempSearchResult.isNullOrEmpty()) {
        null
    } else {
        val nextOffset = offset + loadSize
        if (nextOffset >= totalSize) {
            null
        } else {
            nextOffset
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CryptoModel>): Int? {
        return null
    }

}