package org.demo.lowsproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.fragment_home.*
import org.demo.lowsproject.R
import org.demo.lowsproject.domain.model.TvShowModel
import org.demo.lowsproject.ui.MainActivity
import org.demo.lowsproject.ui.detail.ID_KEY
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), View.OnClickListener {

    private val homeViewModel by viewModel<HomeViewModel>()
    var loadingRecyclerView = false
    var shouldPaginate = true
    var page: Int = 1

    private val listAdapter by lazy {
        TvShowAdapter(
            cardClickListener = { homeViewModel.dispatchViewAction(HomeViewAction.CardClick(it)) },
            loadImageCallback = { image, imageView -> loadCardImage(image, imageView) }
        )
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        observeViewState()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_show_list.adapter = listAdapter
        tv_show_list.addOnScrollListener(onScrollListener {
            homeViewModel.dispatchViewAction(HomeViewAction.Paginate(it))
        })
        search_button.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            search_button -> searchByText(search_serie_by_name.text.toString())
        }
    }

    private fun observeViewState() {
        homeViewModel.viewState.action.observe(
            viewLifecycleOwner,
            Observer {
                when (it) {
                    is HomeViewState.Action.ShowLoading -> (activity as MainActivity).showLoading()
                    is HomeViewState.Action.ShowTvShowList -> showTvShowList(it.list)
                    is HomeViewState.Action.GoToTvShowDetail -> goToTvShowDetail(it.id)
                    is HomeViewState.Action.ShowEmptyState -> showEmptyState()
                    is HomeViewState.Action.ClearList -> clearList()
                    is HomeViewState.Action.ShowTvShowListByText -> showTvShowListByText(it.list)
                }
            }
        )
    }

    private fun showTvShowListByText(list: List<TvShowModel>?) {
        (activity as MainActivity).hideLoading()
        loadingRecyclerView = true
        listAdapter.submitList(list)
    }

    private fun clearList() {
        (activity as MainActivity).showLoading()
        listAdapter.submitList(emptyList())
        page = 1
    }

    private fun showEmptyState() {
        (activity as MainActivity).hideLoading()
        empty_state_label.visibility = VISIBLE
    }

    private fun goToTvShowDetail(id: Int) {
        findNavController().navigate(R.id.action_title_to_about, bundleOf(Pair(ID_KEY, id)))
    }

    private fun showTvShowList(list: List<TvShowModel>?) {
        (activity as MainActivity).hideLoading()
        loadingRecyclerView = false

        list?.let {
            empty_state_label.visibility = GONE
            val newList = listAdapter.currentList.toMutableList()
            newList.addAll(it.asIterable())
            listAdapter.submitList(newList)
        }
    }

    private fun searchByText(textSearch: String) {
        homeViewModel.dispatchViewAction(HomeViewAction.TextSearchClick(textSearch))
    }

    private fun loadCardImage(image: String, imageView: ImageView) {
        Glide
            .with(this)
            .load(image)
            .into(imageView)
    }

    private fun onScrollListener(callback: (Int) -> Unit): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            var pastVisibleItems = 0
            var visibleItemCount = 0
            var totalItemCount = 0
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                visibleItemCount = tv_show_list.layoutManager?.childCount ?: 0
                totalItemCount = tv_show_list.layoutManager?.itemCount ?: 0
                pastVisibleItems =
                    (tv_show_list.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if (!loadingRecyclerView && shouldPaginate) {
                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount / 2) {
                        loadingRecyclerView = true
                        callback(page++)
                    }
                }
            }
        }
    }
}