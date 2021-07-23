package org.demo.lowsproject.domain.usecase

import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.demo.lowsproject.CoroutinesTestRule
import org.demo.lowsproject.data.repository.TvShowRepository
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

@ExperimentalCoroutinesApi
class RemoveFavoriteTvShowCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val tvShowRepository = mockk<TvShowRepository>()
    private val removeFavoriteTvShowById = RemoveFavoriteTvShowById(tvShowRepository, coroutinesTestRule.testDispatcherProvider)

    @Test
    fun callGetShowByPage_shouldReturnListTvShowModel() = runBlockingTest {
        val id = Random(currentTime).nextInt()
        prepareScenario()

        removeFavoriteTvShowById(id)

        coVerify(exactly = 1) { tvShowRepository.removeFavoriteTvShowById(id) }
    }

    private fun prepareScenario() {
        coEvery { tvShowRepository.removeFavoriteTvShowById(any()) } just runs
    }
}