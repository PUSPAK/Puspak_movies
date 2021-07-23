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

@ExperimentalCoroutinesApi
class GetFavoriteListUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val tvShowRepository = mockk<TvShowRepository>()
    private val getFavoriteList = GetFavoriteList(tvShowRepository, coroutinesTestRule.testDispatcherProvider)

    @Test
    fun callGetShowByPage_shouldReturnListTvShowModel() = runBlockingTest {
        prepareScenario()

        getFavoriteList()

        coVerify(exactly = 1) { tvShowRepository.getAllFavoriteTvShow() }
    }

    private fun prepareScenario(list: List<TvShowModel> = listOf(TvShowModel(1, "", ""))) {
        coEvery { tvShowRepository.getAllFavoriteTvShow() } returns list
    }
}