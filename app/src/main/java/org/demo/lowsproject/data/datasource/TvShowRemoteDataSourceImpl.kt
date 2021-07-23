package org.demo.lowsproject.data.datasource

import org.demo.lowsproject.data.Resource
import org.demo.lowsproject.data.ResponseHandler
import org.demo.lowsproject.data.TvMazeApi
import org.demo.lowsproject.data.mapper.TvShowDetailDtoToTvShowDetailModelMapper
import org.demo.lowsproject.data.mapper.TvShowDtoToTvShowModelMapper
import org.demo.lowsproject.data.mapper.TvShowSearchDtoToTvShowModelMapper
import org.demo.lowsproject.domain.model.TvShowDetailModel
import org.demo.lowsproject.domain.model.TvShowModel


class TvShowRemoteDataSourceImpl(
    private val api: TvMazeApi,
    private val responseHandler: ResponseHandler,
    private val tvShowModelMapperTv: TvShowDtoToTvShowModelMapper,
    private val tvShowSearchDtoToTvShowModelMapper: TvShowSearchDtoToTvShowModelMapper,
    private val tvShowDetailDtoToTvShowDetailModelMapper: TvShowDetailDtoToTvShowDetailModelMapper
) : TvShowRemoteDataSource {
    override suspend fun getShowsByPage(page: Int): Resource<List<TvShowModel>> {
        return try {
            val response = api.getShowsByPage(page)
            return responseHandler.handleSuccess(tvShowModelMapperTv.mapFrom(response))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun getTvShowsByText(searchText: String): Resource<List<TvShowModel>> {
        return try {
            val response = api.getTvShowsByText(searchText)
            return responseHandler.handleSuccess(tvShowSearchDtoToTvShowModelMapper.mapFrom(response))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun getTvShowById(id: Int): Resource<TvShowDetailModel> {
        return try {
            val response = api.getTvShowById(id)
            return responseHandler.handleSuccess(tvShowDetailDtoToTvShowDetailModelMapper.mapFrom(response))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

}