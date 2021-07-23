package org.demo.lowsproject.data.repository

import org.demo.lowsproject.data.datasource.EpisodeRemoteDataSource


class EpisodeRepositoryImpl(
        private val episodeRemoteDataSource: EpisodeRemoteDataSource
) : EpisodeRepository {
    override suspend fun getTvEpisodesByShowId(id: Int) = episodeRemoteDataSource.getTvEpisodesByShowId(id)
}