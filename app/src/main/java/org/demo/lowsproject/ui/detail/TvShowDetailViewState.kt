package org.demo.lowsproject.ui.detail

import org.demo.lowsproject.domain.model.EpisodeBySeasonModel
import org.demo.lowsproject.domain.model.TvShowDetailModel
import org.demo.lowsproject.ui.base.SingleLiveEvent


class TvShowDetailViewState {
    val action: SingleLiveEvent<Action> = SingleLiveEvent()

    sealed class Action {
        object ShowLoading : Action()
        object LoadAsFavorite : Action()
        data class LoadInfo(val tvShowDetailModel: TvShowDetailModel?) : Action()
        class LoadEpisodes(val episodes: EpisodeBySeasonModel?) : Action()
    }
}
