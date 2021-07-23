package org.demo.lowsproject.data.datasource

import org.demo.lowsproject.data.Resource
import org.demo.lowsproject.data.ResponseHandler
import org.demo.lowsproject.data.TvMazeApi
import org.demo.lowsproject.data.mapper.EpisodeDtoToEpisodeModelMapper
import org.demo.lowsproject.domain.model.EpisodeModel


class EpisodeRemoteDataSourceImpl(
    private val api: TvMazeApi,
    private val responseHandler: ResponseHandler,
    private val episodeModelMapper: EpisodeDtoToEpisodeModelMapper
) : EpisodeRemoteDataSource {
    override suspend fun getTvEpisodesByShowId(id: Int): Resource<List<EpisodeModel>> {
        return try {
            val response = api.getTvEpisodesByShowId(id)
            return responseHandler.handleSuccess(episodeModelMapper.mapFrom(response))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}