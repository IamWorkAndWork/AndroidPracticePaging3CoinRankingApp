package app.practice.cryptowongnaitest.presentation.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.practice.cryptowongnaitest.databinding.ItemCryptoDefaultBinding
import app.practice.cryptowongnaitest.databinding.ItemCryptoTitleBinding
import app.practice.cryptowongnaitest.domain.models.UiModel

class CryptoTitleViewHolder(private val binding: ItemCryptoTitleBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): CryptoTitleViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemCryptoTitleBinding.inflate(inflater, parent, false)
            return CryptoTitleViewHolder(binding)
        }
    }

    fun bind(model: UiModel.CryptoTitleItem) {
        with(binding) {
            uiModel = model.crypto
        }
    }

}