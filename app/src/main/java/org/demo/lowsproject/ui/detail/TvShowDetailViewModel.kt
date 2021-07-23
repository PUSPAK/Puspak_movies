package org.demo.lowsproject.ui.detail

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.demo.lowsproject.data.Status
import org.demo.lowsproject.domain.model.TvShowDetailModel
import org.demo.lowsproject.domain.model.TvShowModel
import org.demo.lowsproject.domain.usecase.interfaces.*
import org.demo.lowsproject.ui.base.BaseViewModel

class TvShowDetailViewModel(
    private val getTvShowById: GetTvShowByIdUseCase,
    private val getEpisodeByShow: GetEpisodeByShowUseCase,
    private val saveFavoriteTvShow: SaveFavoriteTvShowUseCase,
    private val getFavoriteTvShowById: GetFavoriteTvShowByIdUseCase,
    private val removeFavoriteTvShowById: RemoveFavoriteTvShowByIdUseCase,
    override val viewState: TvShowDetailViewState
) : BaseViewModel<TvShowDetailViewState, TvShowDetailViewAction>() {

    private var tvShowDetailModel: TvShowDetailModel? = null

    override fun dispatchViewAction(viewAction: TvShowDetailViewAction) {
        when (viewAction) {
            is TvShowDetailViewAction.Init -> loadScreen(viewAction.id)
            is TvShowDetailViewAction.LoadEpisodes -> loadEpisodes(viewAction.id)
            is TvShowDetailViewAction.AddToFavoritesClick -> addToFavorites()
            TvShowDetailViewAction.RemoveFromFavoritesClick -> removeFromFavorites()
        }
    }

    private fun removeFromFavorites() {
        tvShowDetailModel?.let { detailt ->
            viewModelScope.launch {
                removeFavoriteTvShowById(detailt.id)
            }
        }
    }

    private fun addToFavorites() {
        tvShowDetailModel?.let { detailt ->
            viewModelScope.launch {
                saveFavoriteTvShow(
                    TvShowModel(
                        id = detailt.id,
                        name = detailt.name,
                        image = detailt.image
                    )
                )
            }
        }
    }

    private fun loadEpisodes(id: Int) {
        viewModelScope.launch() {
            val result = getEpisodeByShow(id)
            if (result.status == Status.SUCCESS) {
                viewState.action.postValue(TvShowDetailViewState.Action.LoadEpisodes(result.data))
            }
        }
    }

    private fun loadScreen(id: Int) {
        viewState.action.postValue(TvShowDetailViewState.Action.ShowLoading)
        viewModelScope.launch {
            val result = getTvShowById(id)
            if (result.status == Status.SUCCESS) {
                tvShowDetailModel = result.data
                viewState.action.postValue(TvShowDetailViewState.Action.LoadInfo(result.data))
            }
        }

        viewModelScope.launch {
            val result = getFavoriteTvShowById(id)
            result?.let {
                viewState.action.postValue(TvShowDetailViewState.Action.LoadAsFavorite)
            }
        }
    }
}