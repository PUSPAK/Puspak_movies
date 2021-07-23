package org.demo.lowsproject.domain.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.demo.lowsproject.CoroutinesTestRule
import org.demo.lowsproject.data.Resource
import org.demo.lowsproject.data.Status
import org.demo.lowsproject.data.repository.EpisodeRepository
import org.demo.lowsproject.domain.model.EpisodeItemResult
import org.demo.lowsproject.domain.model.EpisodeModel
import org.demo.lowsproject.domain.model.ResultType
import org.demo.lowsproject.domain.model.SeasonHeader
import org.junit.Rule
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class GetEpisodeByShowUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val episodeRepository = mockk<EpisodeRepository>()
    private val getEpisodeByShow = GetEpisodeByShow(episodeRepository, coroutinesTestRule.testDispatcherProvider)

    @Test
    fun callGetShowByPage_shouldReturnListTvShowModel() = runBlockingTest {
        val list = listOf(
            EpisodeModel(1, 1, "", 0, "", ""),
            EpisodeModel(2, 1, "", 0, "", ""),
            EpisodeModel(3, 2, "", 0, "", "")
        )
        prepareScenario(list)
        val id = Random(currentTime).nextInt()

        val episodeByShow = getEpisodeByShow(id)

        assertEquals(5, episodeByShow.data?.list?.size)

        assertEquals(ResultType.SEASON_HEADER, episodeByShow.data?.list?.get(0)?.resultType)
        assertEquals(1, (episodeByShow.data?.list?.get(0) as SeasonHeader).seasonNumber)

        assertEquals(ResultType.EPISODE, episodeByShow.data?.list?.get(1)?.resultType)
        assertEquals(1, (episodeByShow.data?.list?.get(1) as EpisodeItemResult).id)

        assertEquals(ResultType.EPISODE, episodeByShow.data?.list?.get(2)?.resultType)
        assertEquals(2, (episodeByShow.data?.list?.get(2) as EpisodeItemResult).id)

        assertEquals(ResultType.SEASON_HEADER, episodeByShow.data?.list?.get(3)?.resultType)
        assertEquals(2, (episodeByShow.data?.list?.get(3) as SeasonHeader).seasonNumber)

        assertEquals(ResultType.EPISODE, episodeByShow.data?.list?.get(4)?.resultType)
        assertEquals(3, (episodeByShow.data?.list?.get(4) as EpisodeItemResult).id)

        coVerify(exactly = 1) { episodeRepository.getTvEpisodesByShowId(id) }
    }

    private fun prepareScenario(list: List<EpisodeModel> = listOf(EpisodeModel(1, 1, "",0, "", ""))) {
        coEvery { episodeRepository.getTvEpisodesByShowId(any()) } returns Resource(
            status = Status.SUCCESS,
            data = list,
            message = null
        )
    }
}