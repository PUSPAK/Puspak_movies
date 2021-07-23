package org.demo.lowsproject.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_episode_detail.*
import kotlinx.android.synthetic.main.tv_show_detail_fragment.*
import kotlinx.android.synthetic.main.tv_show_detail_fragment.episode_number
import kotlinx.android.synthetic.main.tv_show_detail_fragment.episode_poster
import kotlinx.android.synthetic.main.tv_show_detail_fragment.episode_season
import kotlinx.android.synthetic.main.tv_show_detail_fragment.episode_summary
import kotlinx.coroutines.launch
import org.demo.lowsproject.R
import org.demo.lowsproject.domain.model.EpisodeItemResult

private const val ARG_PARAM1 = "param1"

class EpisodeDetailFragment : DialogFragment() {
    private var episodeItemResult: EpisodeItemResult? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_episode_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            episodeItemResult = it.getParcelable(ARG_PARAM1)
            episodeItemResult?.let { episode ->
                episode_name.text = episode.name

                episode_number.text = getString(R.string.episode_deatil_number, episode.number.toString())

                episode_season.text = getString(R.string.episode_deatil_season, episode.season.toString())

                episode_summary.text = HtmlCompat.fromHtml(episode.summary,
                    HtmlCompat.FROM_HTML_MODE_COMPACT)

                Glide.with(this)
                    .load(episode.image)
                    .into(episode_poster)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(episodeItemResult: EpisodeItemResult) =
            EpisodeDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, episodeItemResult)
                }
            }
    }
}