package org.demo.lowsproject.data.repository

import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.demo.lowsproject.data.Resource
import org.demo.lowsproject.data.Status
import org.demo.lowsproject.data.datasource.TvShowLocalDataSource
import org.demo.lowsproject.data.datasource.TvShowRemoteDataSource
import org.demo.lowsproject.domain.model.ScheduleModel
import org.demo.lowsproject.domain.model.TvShowDetailModel
import org.demo.lowsproject.domain.model.TvShowModel
import org.junit.Test
import java.util.*
import kotlin.collections.RandomAccess

@ExperimentalCoroutinesApi
class TvShowRepositoryTest {

    private val tvShowRemoteDataSource = mockk<TvShowRemoteDataSource>()
    private val tvShowLocalDataSource = mockk<TvShowLocalDataSource>()
    private val tvShowRepository = TvShowRepositoryImpl(tvShowRemoteDataSource, tvShowLocalDataSource)

    @Test
    fun callGetShowByPage_shouldReturnListTvShowModel() = runBlockingTest {
        prepareScenario()
        val page = Random(currentTime).nextInt()

        tvShowRepository.getTvShowsByPage(page)

        coVerify(exactly = 1) { tvShowRemoteDataSource.getShowsByPage(page) }
    }

    @Test
    fun callGetShowByText_shouldReturnListTvShowModel() = runBlockingTest {
        prepareScenario()
        val searchText = "Text"

        tvShowRepository.getTvShowsByText(searchText)

        coVerify(exactly = 1) { tvShowRemoteDataSource.getTvShowsByText(searchText) }
    }

    @Test
    fun callGetShowById_shouldReturnTvShowDetailModel() = runBlockingTest {
        prepareScenario()
        val id = kotlin.random.Random(currentTime).nextInt()

        tvShowRepository.getTvShowById(id)

        coVerify(exactly = 1) { tvShowRemoteDataSource.getTvShowById(id) }
    }

    @Test
    fun callSaveFavoriteTvShow_shouldReturnTvShowDetailModel() = runBlockingTest {
        prepareScenario()
        val tvShowModel = TvShowModel(1, "", "")

        tvShowRepository.saveFavoriteTvShow(tvShowModel)

        coVerify(exactly = 1) { tvShowLocalDataSource.insertTvShow(tvShowModel) }
    }

    @Test
    fun callGetFavoriteTvShowById_shouldReturnTvShowDetailModel() = runBlockingTest {
        prepareScenario()
        val id = Random(currentTime).nextInt()

        tvShowRepository.getFavoriteTvShowById(id)

        coVerify(exactly = 1) { tvShowLocalDataSource.getById(id) }
    }

    @Test
    fun callRemoveFavoriteTvShowById_shouldReturnTvShowDetailModel() = runBlockingTest {
        prepareScenario()
        val id = Random(currentTime).nextInt()

        tvShowRepository.removeFavoriteTvShowById(id)

        coVerify(exactly = 1) { tvShowLocalDataSource.removeTvShow(id) }
    }

    @Test
    fun callGetAllFavoriteTvShow_shouldReturnTvShowDetailModel() = runBlockingTest {
        prepareScenario()

        tvShowRepository.getAllFavoriteTvShow()

        coVerify(exactly = 1) { tvShowLocalDataSource.getAllTvShow() }
    }

    private fun prepareScenario(
        list: List<TvShowModel> = listOf(TvShowModel(1, "", "")),
        detail : TvShowDetailModel = TvShowDetailModel(1, "", "", emptyList(),
                    ScheduleModel("", emptyList()), ""),
        tvShowModel: TvShowModel = TvShowModel(1, "", "")) {
        coEvery { tvShowRemoteDataSource.getShowsByPage(any()) } returns Resource(
            status = Status.SUCCESS,
            data = list,
            message = null
        )

        coEvery { tvShowRemoteDataSource.getTvShowsByText(any()) } returns Resource(
            status = Status.SUCCESS,
            data = list,
            message = null
        )

        coEvery { tvShowRemoteDataSource.getTvShowById(any()) } returns Resource(
                status = Status.SUCCESS,
                data = detail,
                message = null
        )

        coEvery { tvShowLocalDataSource.insertTvShow(any()) } just Runs
        coEvery { tvShowLocalDataSource.getById(any()) } returns tvShowModel
        coEvery { tvShowLocalDataSource.removeTvShow(any()) } just Runs
        coEvery { tvShowLocalDataSource.getAllTvShow() } returns list
    }
}