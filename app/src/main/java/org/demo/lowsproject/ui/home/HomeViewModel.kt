package org.demo.lowsproject.ui.home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.demo.lowsproject.domain.usecase.interfaces.GetTvShowByPageUseCase
import org.demo.lowsproject.domain.usecase.interfaces.GetTvShowByTextUseCase
import org.demo.lowsproject.ui.base.BaseViewModel

class HomeViewModel(
    private val getTvShowByPage: GetTvShowByPageUseCase,
    private val getTvShowByTextUseCase: GetTvShowByTextUseCase,
    override val viewState: HomeViewState
) : BaseViewModel<HomeViewState, HomeViewAction>() {

    init {
        viewState.action.postValue(HomeViewState.Action.ShowLoading)

        viewModelScope.launch {
            viewState.action.postValue(HomeViewState.Action.ShowTvShowList(getTvShowByPage().data))
        }
    }

    override fun dispatchViewAction(viewAction: HomeViewAction) {
        when (viewAction) {
            is HomeViewAction.Paginate -> paginate(viewAction.page)
            is HomeViewAction.CardClick -> cardClick(viewAction.id)
            is HomeViewAction.TextSearchClick -> textSearch(viewAction.textSearch)
        }
    }

    private fun textSearch(textSearch: String) {
        viewState.action.postValue(HomeViewState.Action.ClearList)

        if (textSearch.isEmpty()) {
            paginate(0)
        } else {
            viewModelScope.launch {
                val result = getTvShowByTextUseCase(textSearch)
                if (result.data.isNullOrEmpty()) {
                    viewState.action.postValue(HomeViewState.Action.ShowEmptyState)
                } else {
                    viewState.action.postValue(HomeViewState.Action.ShowTvShowListByText(result.data))
                }
            }
        }
    }

    private fun cardClick(id: Int) {
        viewState.action.postValue(HomeViewState.Action.GoToTvShowDetail(id))
    }

    private fun paginate(page: Int) {
        viewModelScope.launch {
            val result = getTvShowByPage(page)
            viewState.action.postValue(HomeViewState.Action.ShowTvShowList(result.data))
        }
    }
}