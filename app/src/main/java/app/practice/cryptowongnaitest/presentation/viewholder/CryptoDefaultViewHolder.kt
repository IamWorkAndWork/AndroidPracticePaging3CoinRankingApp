package app.practice.cryptowongnaitest.presentation.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.practice.cryptowongnaitest.databinding.ItemCryptoDefaultBinding
import app.practice.cryptowongnaitest.domain.models.UiModel

class CryptoDefaultViewHolder(private val binding: ItemCryptoDefaultBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): CryptoDefaultViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemCryptoDefaultBinding.inflate(inflater, parent, false)
            return CryptoDefaultViewHolder(binding)
        }
    }

    fun bind(model: UiModel.CryptoDefaultItem, listener: (UiModel) -> Unit) {
        with(binding) {
            uiModel = model.crypto

            root.setOnClickListener {
                listener.invoke(model)
            }
        }
    }

}