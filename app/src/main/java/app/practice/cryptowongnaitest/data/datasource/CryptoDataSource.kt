package app.practice.cryptowongnaitest.data.datasource

import app.practice.cryptowongnaitest.data.api.ApiService
import com.example.data.model.response.Crypto

interface CryptoDataSource {
    suspend fun searchCoins(offset: Int, limit: Int): Result<List<Crypto>?>
    suspend fun searchCoinsByPrefix(prefix: String): Result<List<Crypto>?>
    suspend fun searchCoinsBySymbols(symbols: String): Result<List<Crypto>?>
    suspend fun searchCoinsBySlugs(slugs: String): Result<List<Crypto>?>
    suspend fun searchCoinsByIds(ids: String): Result<List<Crypto>?>
}

class CryptoDataSourceImpl(
    private val apiService: ApiService
) : CryptoDataSource {
    override suspend fun searchCoins(offset: Int, limit: Int): Result<List<Crypto>?> {
        return try {
            val response = apiService.searchCoins(offset = offset, limit = limit)
            Result.success(response.data?.cryptos)
        } catch (error: Exception) {
            val errorCause = RuntimeException("something went wrong : ${error.localizedMessage}")
            Result.failure(errorCause)
        }
    }

    override suspend fun searchCoinsByPrefix(prefix: String): Result<List<Crypto>?> {
        return try {
            val response = apiService.searchCoinsByPrefix(prefix = prefix)
            Result.success(response.data?.cryptos)
        } catch (error: Exception) {
            val errorCause = RuntimeException("something went wrong : ${error.localizedMessage}")
            Result.failure(errorCause)
        }
    }

    override suspend fun searchCoinsBySymbols(symbols: String): Result<List<Crypto>?> {
        return try {
            val response = apiService.searchCoinsBySymbols(symbols = symbols)
            Result.success(response.data?.cryptos)
        } catch (error: Exception) {
            val errorCause =
                RuntimeException("something went wrong : ${error.localizedMessage}")
            Result.failure(errorCause)
        }
    }

    override suspend fun searchCoinsBySlugs(slugs: String): Result<List<Crypto>?> {
        return try {
            val response = apiService.searchCoinsBySlugs(slugs = slugs)
            Result.success(response.data?.cryptos)
        } catch (error: Exception) {
            val errorCause = RuntimeException("something went wrong : ${error.localizedMessage}")
            Result.failure(errorCause)
        }
    }

    override suspend fun searchCoinsByIds(ids: String): Result<List<Crypto>?> {
        return try {
            val response = apiService.searchCoinsByIds(ids = ids)
            Result.success(response.data?.cryptos)
        } catch (error: Exception) {
            val errorCause = RuntimeException("something went wrong : ${error.localizedMessage}")
            Result.failure(errorCause)
        }
    }

}