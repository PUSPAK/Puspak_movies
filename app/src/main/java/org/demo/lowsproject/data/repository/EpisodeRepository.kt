package org.demo.lowsproject.data.repository

import org.demo.lowsproject.data.Resource
import org.demo.lowsproject.domain.model.EpisodeModel


interface EpisodeRepository {
    suspend fun getTvEpisodesByShowId(id: Int): Resource<List<EpisodeModel>>
}