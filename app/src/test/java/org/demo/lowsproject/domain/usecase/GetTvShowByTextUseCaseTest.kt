package com.otero.tvmazeapp.domain.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.demo.lowsproject.data.Resource
import org.demo.lowsproject.data.Status
import org.demo.lowsproject.data.repository.TvShowRepository
import org.demo.lowsproject.domain.model.TvShowModel
import org.demo.lowsproject.domain.usecase.GetTvShowByText
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class GetTvShowByTextUseCaseTest  {

    private val tvShowRepository = mockk<TvShowRepository>()
    private val getTvShowByText = GetTvShowByText(tvShowRepository)

    @Test
    fun callGetShowByPage_shouldReturnListTvShowModel() = runBlockingTest {
        prepareScenario()

        val searchText = "text"
        getTvShowByText(searchText)

        coVerify(exactly = 1) { tvShowRepository.getTvShowsByText(searchText) }
    }

    private fun prepareScenario(list: List<TvShowModel> = listOf(TvShowModel(1, "", ""))) {
        coEvery { tvShowRepository.getTvShowsByText(any()) } returns Resource(
            status = Status.SUCCESS,
            data = list,
            message = null
        )
    }
}