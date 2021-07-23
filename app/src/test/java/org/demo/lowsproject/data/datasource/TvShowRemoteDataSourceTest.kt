package org.demo.lowsproject.data.datasource

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.demo.lowsproject.data.ResponseHandler
import org.demo.lowsproject.data.TvMazeApi
import org.demo.lowsproject.data.dto.ImageDto
import org.demo.lowsproject.data.dto.TvShowDetailDto
import org.demo.lowsproject.data.dto.TvShowDto
import org.demo.lowsproject.data.dto.TvShowSearchDto
import org.demo.lowsproject.data.mapper.TvShowDetailDtoToTvShowDetailModelMapper
import org.demo.lowsproject.data.mapper.TvShowDtoToTvShowModelMapper
import org.demo.lowsproject.data.mapper.TvShowSearchDtoToTvShowModelMapper
import org.junit.Test

class TvShowRemoteDataSourceTest {
    private val api = mockk<TvMazeApi>()

    private val tvShowRemoteDataSource =
        TvShowRemoteDataSourceImpl(
                api,
                ResponseHandler(),
                TvShowDtoToTvShowModelMapper(),
                TvShowSearchDtoToTvShowModelMapper(),
                TvShowDetailDtoToTvShowDetailModelMapper()
        )

    @Test
    fun getShowsByPage_returnSucessResult() = runBlocking {
        prepareScenario(listOf(TvShowDto(1, null, null)))

        tvShowRemoteDataSource.getShowsByPage(1)

        coVerify(exactly = 1) { api.getShowsByPage(1) }
    }

    @Test
    fun getShowsByText_returnSucessResult() = runBlocking {
        prepareScenario(listOf(TvShowDto(1, null, null)))
        val searchText = "text"

        tvShowRemoteDataSource.getTvShowsByText(searchText)
        coVerify(exactly = 1) { api.getTvShowsByText(searchText) }
    }

    @Test
    fun getShowsById_returnSucessResult() = runBlocking {
        prepareScenario()
        val id = 1

        tvShowRemoteDataSource.getTvShowById(id)
        coVerify(exactly = 1) { api.getTvShowById(id) }
    }


    private fun prepareScenario(list: List<TvShowDto> = emptyList(),
                                listSearch: List<TvShowSearchDto> = emptyList(),
                                tvShowDetailDto: TvShowDetailDto = TvShowDetailDto(1, "",
                                        ImageDto(""), null, null,
                                        null)
    ) {
        coEvery { api.getShowsByPage(any()) } returns list
        coEvery { api.getTvShowsByText(any()) } returns listSearch
        coEvery { api.getTvShowById(any()) } returns tvShowDetailDto
    }
}