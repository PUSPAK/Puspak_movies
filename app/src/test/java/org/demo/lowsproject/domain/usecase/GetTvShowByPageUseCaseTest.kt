package org.demo.lowsproject.domain.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.demo.lowsproject.data.Resource
import org.demo.lowsproject.data.Status
import org.demo.lowsproject.data.repository.TvShowRepository
import org.demo.lowsproject.domain.model.TvShowModel
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class GetTvShowByPageUseCaseTest {

    private val tvShowRepository = mockk<TvShowRepository>()
    private val getShowByPage = GetTvShowByPage(tvShowRepository)

    @Test
    fun callGetShowByPage_shouldReturnListTvShowModel() = runBlockingTest {
        prepareScenario()
        val page = Random(currentTime).nextInt()

        getShowByPage(page)

        coVerify(exactly = 1) { tvShowRepository.getTvShowsByPage(page) }
    }

    private fun prepareScenario(list: List<TvShowModel> = listOf(TvShowModel(1, "", ""))) {
        coEvery { tvShowRepository.getTvShowsByPage(any()) } returns Resource(
            status = Status.SUCCESS,
            data = list,
            message = null
        )
    }
}