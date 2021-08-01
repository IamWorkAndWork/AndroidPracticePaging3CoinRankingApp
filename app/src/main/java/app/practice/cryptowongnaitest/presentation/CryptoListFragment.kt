package app.practice.cryptowongnaitest.presentation

import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import app.practice.cryptowongnaitest.R
import app.practice.cryptowongnaitest.databinding.FragmentCryptoListBinding
import app.practice.cryptowongnaitest.presentation.base.BaseFragment
import app.practice.cryptowongnaitest.presentation.models.UiAction
import app.practice.cryptowongnaitest.presentation.models.UiState
import app.practice.cryptowongnaitest.utils.NetworkListener
import app.practice.cryptowongnaitest.utils.hideKeyboard
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CryptoListFragment : BaseFragment<FragmentCryptoListBinding>() {

    private val cryptoListViewModel by viewModel<CryptoListViewModel>()

    private val cryptoEpoxyController by lazy {
        CryptoEpoxyController()
    }
    private val networkListener by lazy {
        NetworkListener()
    }
    private var isPullToRefresh = false

    override fun layout(): Int {
        return R.layout.fragment_crypto_list
    }

    override fun init() {
        checkInternetConnection()
        binding.observeViewModel()
        binding.initEvent()
        binding.bindState(
            uiState = cryptoListViewModel.state,
            uiActions = cryptoListViewModel.accept
        )
    }

    private fun checkInternetConnection() {
        lifecycleScope.launch {
            networkListener.checkNetworkAvailable(requireContext())
                .collect { isOnline ->
                    cryptoListViewModel.networkStatus = isOnline
                    showNetworkStatus(isOnline)
                }
        }
    }

    private fun showNetworkStatus(isOnline: Boolean) {
        if (!isOnline) {
            Toast.makeText(
                requireContext(),
                getString(R.string.no_internet_connection),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun FragmentCryptoListBinding.bindState(
        uiState: StateFlow<UiState>,
        uiActions: (UiAction) -> Unit
    ) {

        bindSearch(
            uiState = uiState,
            onQueryChanged = uiActions
        )
        bindList(
            uiState = uiState,
        )

    }

    private fun FragmentCryptoListBinding.initEvent() {
        swipeRefreshLayout?.setOnRefreshListener {
            refreshData(onQueryChanged = cryptoListViewModel.accept)
        }
    }

    private fun refreshData(onQueryChanged: (UiAction) -> Unit) {
        isPullToRefresh = true
        cryptoEpoxyController.refresh()
        onQueryChanged(
            UiAction.Search(searchQuery = "")
        )
    }

    private fun FragmentCryptoListBinding.bindList(uiState: StateFlow<UiState>) {

        cryptoRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cryptoEpoxyController.adapter
        }

        val pagingData = uiState.map {
            it.pagingData
        }.distinctUntilChanged()

        lifecycleScope.launch {
            pagingData.collectLatest { pagingData ->
                cryptoEpoxyController.submitData(pagingData = pagingData)
            }
        }

        cryptoEpoxyController.addLoadStateListener { loadState ->

            val isListEmpty =
                loadState.refresh is LoadState.NotLoading && cryptoEpoxyController.adapter.itemCount == 0

            noDataTextView.isVisible = isListEmpty

//            cryptoRecyclerView.isVisible =
//                loadState.source.refresh is LoadState.NotLoading || loadState.mediator?.refresh is LoadState.NotLoading

            val isLoading = loadState.source.refresh is LoadState.Loading
            progressBar.isVisible = isLoading

//            println("pagingData isListEmpty = "+isListEmpty+" | isLoading = "+isLoading+" | "+ cryptoEpoxyController.adapter.itemCount)

            if (isLoading && isPullToRefresh) {
                isPullToRefresh = false
            }
            swipeRefreshLayout?.isRefreshing = isLoading && isPullToRefresh
        }

    }

    private fun FragmentCryptoListBinding.bindSearch(
        uiState: StateFlow<UiState>,
        onQueryChanged: (UiAction) -> Unit
    ) {
        queryEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updateCryptoListFromInput(onQueryChanged)
                true
            } else {
                false
            }
        }

        queryEditText.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateCryptoListFromInput(onQueryChanged)
                true
            } else {
                false
            }
        }

        lifecycleScope.launch {
            uiState
                .map { uiState ->
                    uiState.query
                }.distinctUntilChanged()
                .collect(queryEditText::setText)
        }
    }

    private fun FragmentCryptoListBinding.updateCryptoListFromInput(onQueryChanged: (UiAction) -> Unit) {
        if (cryptoListViewModel.networkStatus) {
            queryEditText.text?.trim()?.let { queryText ->
                onQueryChanged(
                    UiAction.Search(searchQuery = queryText.toString())
                )
            }
            queryEditText.hideKeyboard(requireActivity())
        }
    }

    private fun FragmentCryptoListBinding.observeViewModel() {
        cryptoListViewModel.showLoading.observe(viewLifecycleOwner,{
            if (it){
                progressBar.visibility = View.VISIBLE
            }
            else{
                progressBar.visibility = View.GONE
            }
        })
    }

}




