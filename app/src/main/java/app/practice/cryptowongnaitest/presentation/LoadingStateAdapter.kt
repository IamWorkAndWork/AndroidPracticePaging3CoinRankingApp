package app.practice.cryptowongnaitest.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import app.practice.cryptowongnaitest.databinding.ItemLoadingBinding

class LoadingStateAdapter : LoadStateAdapter<LoadingStateAdapter.CoinLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CoinLoadStateViewHolder {
        val binding = ItemLoadingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinLoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class CoinLoadStateViewHolder(private val binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
        }

    }

}

