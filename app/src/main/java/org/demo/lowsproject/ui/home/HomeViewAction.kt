package org.demo.lowsproject.ui.home

sealed class HomeViewAction {
    data class Paginate(val page: Int) : HomeViewAction()
    data class CardClick(val id: Int) : HomeViewAction()
    data class TextSearchClick(val textSearch: String) : HomeViewAction()
}
