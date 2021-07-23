package org.demo.lowsproject.domain.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.demo.lowsproject.data.Resource
import org.demo.lowsproject.data.Status
import org.demo.lowsproject.data.repository.TvShowRepository
import org.demo.lowsproject.domain.model.ScheduleModel
import org.demo.lowsproject.domain.model.TvShowDetailModel
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class GetTvShowByIdUseCaseTest {

    private val tvShowRepository = mockk<TvShowRepository>()
    private val getShowById = GetTvShowById(tvShowRepository)

    @Test
    fun callGetShowByPage_shouldReturnListTvShowModel() = runBlockingTest {
        prepareScenario()
        val id = Random(currentTime).nextInt()

        getShowById(id)

        coVerify(exactly = 1) { tvShowRepository.getTvShowById(id) }
    }

    private fun prepareScenario(list: TvShowDetailModel = TvShowDetailModel(1, "", "",
            emptyList(), ScheduleModel("", emptyList()), "")) {
        coEvery { tvShowRepository.getTvShowById(any()) } returns Resource(
                status = Status.SUCCESS,
                data = list,
                message = null
        )
    }
}