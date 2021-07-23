package org.demo.lowsproject.data.datasource

import org.demo.lowsproject.domain.model.TvShowModel


interface TvShowLocalDataSource {
    suspend fun insertTvShow(tvShowDbo: TvShowModel)
    suspend fun getById(id: Int) :TvShowModel?
    suspend fun removeTvShow(id: Int)
    suspend fun getAllTvShow() : List<TvShowModel>
}