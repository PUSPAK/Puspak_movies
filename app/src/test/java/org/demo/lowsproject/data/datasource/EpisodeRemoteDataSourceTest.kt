package org.demo.lowsproject.data.datasource


import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.demo.lowsproject.data.ResponseHandler
import org.demo.lowsproject.data.TvMazeApi
import org.demo.lowsproject.data.dto.EpisodeDto
import org.demo.lowsproject.data.dto.ImageDto
import org.demo.lowsproject.data.mapper.EpisodeDtoToEpisodeModelMapper
import org.junit.Test

class EpisodeRemoteDataSourceTest {
    private val api = mockk<TvMazeApi>()

    private val episodeRemoteDataSourceImpl =
            EpisodeRemoteDataSourceImpl(
                    api,
                    ResponseHandler(),
                    EpisodeDtoToEpisodeModelMapper()
            )

    @Test
    fun getShowsByPage_returnSucessResult() = runBlocking {
        prepareScenario(listOf(EpisodeDto(1, 1, "", 0, "", ImageDto(""))))

        episodeRemoteDataSourceImpl.getTvEpisodesByShowId(1)

        coVerify(exactly = 1) { api.getTvEpisodesByShowId(1) }
    }


    private fun prepareScenario(list: List<EpisodeDto> = emptyList()) {
        coEvery { api.getTvEpisodesByShowId(any()) } returns list
    }
}