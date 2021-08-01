package app.practice.cryptowongnaitest.presentation.epoxy

import android.content.Context
import android.util.AttributeSet
import app.practice.cryptowongnaitest.R
import app.practice.cryptowongnaitest.databinding.ItemCryptoDefaultBinding
import app.practice.cryptowongnaitest.domain.models.CryptoModel
import app.practice.cryptowongnaitest.presentation.base.BaseEpoxyModel
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT, fullSpan = false)
class CryptoItemDefaultEpoxy @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseEpoxyModel<ItemCryptoDefaultBinding>(context, attrs, defStyleAttr) {

    override fun layout(): Int {
        return R.layout.item_crypto_default
    }

    @ModelProp
    fun setCryptoModel(cryptoModel: CryptoModel) {
        binding.uiModel = cryptoModel
    }

}