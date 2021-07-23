package org.demo.lowsproject.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.tv_show_detail_fragment.*
import org.demo.lowsproject.R
import org.demo.lowsproject.domain.model.EpisodeBySeasonModel
import org.demo.lowsproject.domain.model.TvShowDetailModel
import org.demo.lowsproject.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

const val ID_KEY = "tvShowId"

class TvShowDetailFragment : Fragment(), View.OnClickListener {

    private val viewModel by viewModel<TvShowDetailViewModel>()

    private val listAdapter by lazy {
        EpisodeListAdapter(
            cardClickListener = {
                val ft = childFragmentManager.beginTransaction()
                EpisodeDetailFragment.newInstance(it).show(ft, "Dialog")
            }
        )
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        observeViewState()
        return inflater.inflate(R.layout.tv_show_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt(ID_KEY)
        id?.let {
            viewModel.dispatchViewAction(TvShowDetailViewAction.Init(it))
        }

        tv_show_list.adapter = listAdapter

        favorite_icon.setOnClickListener(this)
        unfavorite_icon.setOnClickListener(this)
    }

    private fun observeViewState() {
        viewModel.viewState.action.observe(
                viewLifecycleOwner,
                Observer {
                    when (it) {
                        is TvShowDetailViewState.Action.LoadInfo -> loadInfo(it.tvShowDetailModel)
                        is TvShowDetailViewState.Action.ShowLoading -> showLoading()
                        is TvShowDetailViewState.Action.LoadEpisodes -> showEpisodes(it.episodes)
                        TvShowDetailViewState.Action.LoadAsFavorite -> loadAsFavorite()
                    }
                }
        )
    }

    private fun loadAsFavorite() {
        favorite_icon.visibility = GONE
        unfavorite_icon.visibility = VISIBLE
    }

    private fun showEpisodes(episodes: EpisodeBySeasonModel?) {
        episode_progress.visibility = GONE
        listAdapter.submitList(episodes?.list)
    }

    private fun showLoading(show: Boolean = true) {
        if (show) {
            (activity as MainActivity).showLoading()
        } else {
            (activity as MainActivity).hideLoading()
        }
    }

    private fun loadInfo(tvShowModel: TvShowDetailModel?) {
        showLoading(false)
        tvShowModel?.let {
            Glide.with(this)
                .load(tvShowModel.image)
                .into(episode_poster)
            tv_show_name.text = tvShowModel.name

            episode_number.text = getString(
                R.string.tv_show_detail_genres_string,
                tvShowModel.genres.joinToString(", ")
            )
            episode_season.text =
                getString(
                    R.string.tv_show_detail_schedule_string,
                    tvShowModel.schedule.time, tvShowModel.schedule.days
                        .joinToString(", ")
                )
            episode_summary.text = HtmlCompat.fromHtml(
                tvShowModel.summary,
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )

            val id = arguments?.getInt(ID_KEY)
            id?.let {
                viewModel.dispatchViewAction(TvShowDetailViewAction.LoadEpisodes(it))
            }
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            favorite_icon -> {
                viewModel.dispatchViewAction(TvShowDetailViewAction.AddToFavoritesClick)
                Toast.makeText(context, getString(R.string.favorite_message), Toast.LENGTH_SHORT)
                    .show()
                favorite_icon.visibility = GONE
                unfavorite_icon.visibility = VISIBLE
            }
            unfavorite_icon -> {
                viewModel.dispatchViewAction(TvShowDetailViewAction.RemoveFromFavoritesClick)
                Toast.makeText(context, getString(R.string.unfavorite_message), Toast.LENGTH_SHORT)
                    .show()
                favorite_icon.visibility = VISIBLE
                unfavorite_icon.visibility = GONE
            }
        }
    }
}