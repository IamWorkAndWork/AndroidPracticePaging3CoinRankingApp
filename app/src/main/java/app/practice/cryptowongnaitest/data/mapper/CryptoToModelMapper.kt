package app.practice.cryptowongnaitest.data.mapper

import app.practice.cryptowongnaitest.domain.models.CryptoModel
import com.example.data.model.response.Crypto
import java.lang.Exception

class CryptoToModelMapper {

    fun toCryptoModelList(cryptoList: Result<List<Crypto>?>): List<CryptoModel>? {
        return if (cryptoList.isSuccess) {
            try {
                cryptoList.getOrNull()?.map(::toCryptoModel)
            } catch (error: Exception) {
                return emptyList()
            }
        } else {
            emptyList()
        }
    }

    private fun toCryptoModel(crypto: Crypto?): CryptoModel {
        return CryptoModel().apply {
            id = crypto?.id?.or(0).toString()
            description = crypto?.description.orEmpty()
            iconUrl = crypto?.iconUrl.orEmpty()
            name = crypto?.name.orEmpty()
        }
    }

}