package org.demo.lowsproject.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.demo.lowsproject.R
import org.demo.lowsproject.domain.model.EpisodeItemResult
import org.demo.lowsproject.domain.model.ResultType
import org.demo.lowsproject.domain.model.SeasonHeader

class EpisodeListAdapter(
        private val cardClickListener: (EpisodeItemResult) -> Unit
) :
        ListAdapter<EpisodeItemResult, RecyclerView.ViewHolder>(ParticipantRulesDiffcalback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ResultType.SEASON_HEADER.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.season_header_item, parent, false)
                SeasonTitleViewHolder(view)
            }
            ResultType.EPISODE.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.episode_list_item, parent, false)
                EpisodeViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.season_header_item, parent, false)
                SeasonTitleViewHolder(view)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item.resultType == ResultType.EPISODE) {
            (holder as EpisodeViewHolder).bind(item, cardClickListener)
        } else {
            (holder as SeasonTitleViewHolder).bind((item as SeasonHeader).seasonNumber)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).resultType.ordinal
    }

    class ParticipantRulesDiffcalback : DiffUtil.ItemCallback<EpisodeItemResult>() {
        override fun areItemsTheSame(
                oldItem: EpisodeItemResult,
                newItem: EpisodeItemResult
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
                oldItem: EpisodeItemResult,
                newItem: EpisodeItemResult
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

class EpisodeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var tvShowName: TextView = itemView.findViewById(R.id.tv_show_name)
    private var tvShowCard: ConstraintLayout = itemView.findViewById(R.id.tv_show_card)

    fun bind(
            item: EpisodeItemResult,
            cardClickListener: (EpisodeItemResult) -> Unit
    ) {
        tvShowName.text = item.name
        tvShowCard.setOnClickListener { cardClickListener(item) }
    }
}

class SeasonTitleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var seasonNumber: TextView = itemView.findViewById(R.id.season_number)

    fun bind(season: Int) {
        seasonNumber.text = season.toString()
    }
}

