package org.demo.lowsproject.data.repository

import org.demo.lowsproject.data.datasource.TvShowLocalDataSource
import org.demo.lowsproject.data.datasource.TvShowRemoteDataSource
import org.demo.lowsproject.domain.model.TvShowModel


class TvShowRepositoryImpl(
    private val tvShowRemote: TvShowRemoteDataSource,
    private val tvShowLocal: TvShowLocalDataSource
) : TvShowRepository {
    override suspend fun getTvShowsByPage(page: Int) = tvShowRemote.getShowsByPage(page)
    override suspend fun getTvShowsByText(searchText: String) =
        tvShowRemote.getTvShowsByText(searchText)
    override suspend fun getTvShowById(id: Int) = tvShowRemote.getTvShowById(id)
    override suspend fun saveFavoriteTvShow(tvShowModel: TvShowModel) =
        tvShowLocal.insertTvShow(tvShowModel)
    override suspend fun getFavoriteTvShowById(id: Int) = tvShowLocal.getById(id)
    override suspend fun removeFavoriteTvShowById(id: Int) = tvShowLocal.removeTvShow(id)
    override suspend fun getAllFavoriteTvShow() = tvShowLocal.getAllTvShow()
}