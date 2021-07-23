package org.demo.lowsproject.domain.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.demo.lowsproject.CoroutinesTestRule
import org.demo.lowsproject.data.repository.TvShowRepository
import org.demo.lowsproject.domain.model.TvShowModel
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

@ExperimentalCoroutinesApi
class GetFavoriteTvShowByIdUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val tvShowRepository = mockk<TvShowRepository>()
    private val getFavoriteTvShowById = GetFavoriteTvShowById(tvShowRepository, coroutinesTestRule.testDispatcherProvider)

    @Test
    fun callGetShowByPage_shouldReturnListTvShowModel() = runBlockingTest {
        prepareScenario()
        val id = Random(currentTime).nextInt()
        getFavoriteTvShowById(id)

        coVerify(exactly = 1) { tvShowRepository.getFavoriteTvShowById(id) }
    }

    private fun prepareScenario(list: TvShowModel = TvShowModel(1, "", "")) {
        coEvery { tvShowRepository.getFavoriteTvShowById(any()) } returns list
    }
}