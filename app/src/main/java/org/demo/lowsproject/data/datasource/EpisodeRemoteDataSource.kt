package org.demo.lowsproject.data.datasource

import org.demo.lowsproject.data.Resource
import org.demo.lowsproject.domain.model.EpisodeModel

interface EpisodeRemoteDataSource {
    suspend fun getTvEpisodesByShowId(page: Int = 0): Resource<List<EpisodeModel>>
}