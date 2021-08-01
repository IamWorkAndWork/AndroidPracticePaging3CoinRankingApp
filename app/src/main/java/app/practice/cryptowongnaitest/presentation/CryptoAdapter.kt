package app.practice.cryptowongnaitest.presentation

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.practice.cryptowongnaitest.R
import app.practice.cryptowongnaitest.domain.models.UiModel
import app.practice.cryptowongnaitest.presentation.viewholder.CryptoDefaultViewHolder
import app.practice.cryptowongnaitest.presentation.viewholder.CryptoTitleViewHolder
import app.practice.cryptowongnaitest.presentation.viewholder.EndOfDataViewHolder
import app.practice.cryptowongnaitest.presentation.viewholder.SeperatorItemViewHolder

class CryptoAdapter(private val listener: (UiModel) -> Unit) :
    PagingDataAdapter<UiModel, RecyclerView.ViewHolder>(diffItem) {

    companion object {
        private val diffItem = object : DiffUtil.ItemCallback<UiModel>() {
            override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is UiModel.CryptoDefaultItem -> {
            R.layout.item_crypto_default
        }
        is UiModel.CryptoTitleItem -> {
            R.layout.item_crypto_title
        }
        is UiModel.EndOfDataItem -> {
            R.layout.item_end_of_data
        }
        else -> {
            R.layout.item_seperator
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is CryptoDefaultViewHolder -> {
                holder.bind(item as UiModel.CryptoDefaultItem, listener)
            }
            is CryptoTitleViewHolder -> {
                holder.bind(item as UiModel.CryptoTitleItem, listener)
            }
            is EndOfDataViewHolder -> {
                holder.bind(item as UiModel.EndOfDataItem)
            }
            is SeperatorItemViewHolder -> {
                holder.bind(item as UiModel.SeparatorItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_crypto_default -> CryptoDefaultViewHolder.from(parent)
            R.layout.item_crypto_title -> CryptoTitleViewHolder.from(parent)
            R.layout.item_end_of_data -> EndOfDataViewHolder.from(parent)
            else -> SeperatorItemViewHolder.from(parent)
        }
    }

}