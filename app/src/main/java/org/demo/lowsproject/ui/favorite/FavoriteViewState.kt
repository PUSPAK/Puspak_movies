package org.demo.lowsproject.ui.favorite

import org.demo.lowsproject.domain.model.TvShowModel
import org.demo.lowsproject.ui.base.SingleLiveEvent


class FavoriteViewState {
    val action: SingleLiveEvent<Action> = SingleLiveEvent()

    sealed class Action {
        data class ShowTvShowList(val list: List<TvShowModel>) : Action()
        data class GoToTvShowDetail(val id: Int) : Action()

        object ShowLoading : Action()
        object ShowEmptyState : Action()
    }
}
