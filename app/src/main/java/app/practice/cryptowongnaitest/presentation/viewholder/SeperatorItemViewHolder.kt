package app.practice.cryptowongnaitest.presentation.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.practice.cryptowongnaitest.databinding.ItemCryptoDefaultBinding
import app.practice.cryptowongnaitest.databinding.ItemCryptoTitleBinding
import app.practice.cryptowongnaitest.databinding.ItemSeperatorBinding
import app.practice.cryptowongnaitest.domain.models.UiModel

class SeperatorItemViewHolder(private val binding: ItemSeperatorBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): SeperatorItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemSeperatorBinding.inflate(inflater, parent, false)
            return SeperatorItemViewHolder(binding)
        }
    }

    fun bind(model: UiModel.SeparatorItem) {

    }

}