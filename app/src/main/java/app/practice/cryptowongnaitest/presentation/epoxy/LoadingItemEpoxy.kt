package app.practice.cryptowongnaitest.presentation.epoxy

import android.content.Context
import android.util.AttributeSet
import app.practice.cryptowongnaitest.R
import app.practice.cryptowongnaitest.databinding.ItemLoadingBinding
import app.practice.cryptowongnaitest.databinding.ItemSeperatorBinding
import app.practice.cryptowongnaitest.presentation.base.BaseEpoxyModel
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT, fullSpan = false)
class LoadingItemEpoxy @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseEpoxyModel<ItemLoadingBinding>(context, attrs, defStyleAttr) {

    override fun layout(): Int {
        return R.layout.item_loading
    }

}