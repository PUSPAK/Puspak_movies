package org.demo.lowsproject.data.datasource

import org.demo.lowsproject.data.Resource
import org.demo.lowsproject.domain.model.TvShowDetailModel
import org.demo.lowsproject.domain.model.TvShowModel


interface TvShowRemoteDataSource {
    suspend fun getShowsByPage(page: Int = 0): Resource<List<TvShowModel>>
    suspend fun getTvShowsByText(searchText: String): Resource<List<TvShowModel>>
    suspend fun getTvShowById(id: Int): Resource<TvShowDetailModel>
}