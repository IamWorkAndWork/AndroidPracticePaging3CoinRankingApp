package app.practice.cryptowongnaitest.presentation.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.practice.cryptowongnaitest.databinding.ItemEndOfDataBinding
import app.practice.cryptowongnaitest.databinding.ItemSeperatorBinding
import app.practice.cryptowongnaitest.domain.models.UiModel

class EndOfDataViewHolder(private val binding: ItemEndOfDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): EndOfDataViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemEndOfDataBinding.inflate(inflater, parent, false)
            return EndOfDataViewHolder(binding)
        }
    }

    fun bind(model: UiModel.EndOfDataItem) {

    }

}