package org.demo.lowsproject.domain.usecase

import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.demo.lowsproject.CoroutinesTestRule
import org.demo.lowsproject.data.repository.TvShowRepository
import org.demo.lowsproject.domain.model.TvShowModel
import org.demo.lowsproject.domain.usecase.SaveFavoriteTvShow
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SaveFavoriteTvShowCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val tvShowRepository = mockk<TvShowRepository>()
    private val saveFavoriteTvShow = SaveFavoriteTvShow(tvShowRepository, coroutinesTestRule.testDispatcherProvider)

    @Test
    fun callGetShowByPage_shouldReturnListTvShowModel() = runBlockingTest {
        val tvShow = TvShowModel(1, "", "")
        prepareScenario()

        saveFavoriteTvShow(tvShow)

        coVerify(exactly = 1) { tvShowRepository.saveFavoriteTvShow(tvShow) }
    }

    private fun prepareScenario() {
        coEvery { tvShowRepository.saveFavoriteTvShow(any()) } just runs
    }
}