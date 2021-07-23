package org.demo.lowsproject.ui.favorite

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
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.demo.lowsproject.R
import org.demo.lowsproject.domain.model.TvShowModel
import org.demo.lowsproject.ui.MainActivity
import org.demo.lowsproject.ui.detail.ID_KEY
import org.demo.lowsproject.ui.home.TvShowAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {

    private val favoriteViewModel by viewModel<FavoriteViewModel>()
    private val listAdapter by lazy {
        TvShowAdapter(
            cardClickListener = {
                favoriteViewModel.dispatchViewAction(
                    FavoriteViewAction.CardClick(
                        it
                    )
                )
            },
            loadImageCallback = { image, imageView -> loadCardImage(image, imageView) }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observeViewState()
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_show_list.adapter = listAdapter
    }

    override fun onResume() {
        super.onResume()
        favoriteViewModel.dispatchViewAction(FavoriteViewAction.LoadFavorites)
    }

    private fun observeViewState() {
        favoriteViewModel.viewState.action.observe(
            viewLifecycleOwner,
            Observer {
                when (it) {
                    is FavoriteViewState.Action.ShowEmptyState -> showEmptyState()
                    is FavoriteViewState.Action.ShowLoading -> showLoading()
                    is FavoriteViewState.Action.ShowTvShowList -> showList(it.list)
                    is FavoriteViewState.Action.GoToTvShowDetail -> goToTvShowDetail(it.id)
                }
            }
        )
    }

    private fun goToTvShowDetail(id: Int) {
        findNavController().navigate(R.id.action_title_to_about, bundleOf(Pair(ID_KEY, id)))
    }

    private fun showList(list: List<TvShowModel>) {
        listAdapter.submitList(list)
    }

    private fun loadCardImage(image: String, imageView: ImageView) {
        Glide
            .with(this)
            .load(image)
            .into(imageView)
    }

    private fun showLoading(show: Boolean = true) {
        if (show) {
            (activity as MainActivity).showLoading()
        } else {
            (activity as MainActivity).hideLoading()
        }
    }

    private fun showEmptyState() {
        favorite_empty_state.visibility = VISIBLE
        tv_show_list.visibility = GONE
    }
}