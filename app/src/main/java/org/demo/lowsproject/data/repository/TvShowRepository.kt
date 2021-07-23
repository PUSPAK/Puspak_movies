package org.demo.lowsproject.data.repository

import org.demo.lowsproject.data.Resource
import org.demo.lowsproject.domain.model.TvShowDetailModel
import org.demo.lowsproject.domain.model.TvShowModel


interface TvShowRepository {
    suspend fun getTvShowsByPage(page: Int = 0): Resource<List<TvShowModel>>
    suspend fun getTvShowsByText(searchText: String): Resource<List<TvShowModel>>
    suspend fun getTvShowById(id: Int): Resource<TvShowDetailModel>
    suspend fun saveFavoriteTvShow(tvShowModel: TvShowModel)
    suspend fun getFavoriteTvShowById(id: Int) : TvShowModel?
    suspend fun removeFavoriteTvShowById(id: Int)
    suspend fun getAllFavoriteTvShow(): List<TvShowModel>?
}