package org.demo.lowsproject.ui.home

import org.demo.lowsproject.domain.model.TvShowModel
import org.demo.lowsproject.ui.base.SingleLiveEvent


class HomeViewState {
    val action: SingleLiveEvent<Action> = SingleLiveEvent()

    sealed class Action {
        object ShowLoading : Action()
        object ShowEmptyState : Action()
        object ClearList : Action()

        data class ShowTvShowList(val list: List<TvShowModel>?) : Action()
        data class ShowTvShowListByText(val list: List<TvShowModel>?) : Action()
        data class GoToTvShowDetail(val id: Int) : Action()
    }
}
