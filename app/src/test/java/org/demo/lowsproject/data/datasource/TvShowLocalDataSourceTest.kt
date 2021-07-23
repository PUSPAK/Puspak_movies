package org.demo.lowsproject.data.datasource

import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.demo.lowsproject.data.dao.TvShowDao
import org.demo.lowsproject.data.database.AppDatabase
import org.demo.lowsproject.data.dbo.TvShowDbo
import org.demo.lowsproject.data.mapper.TvShowDboToTvShowModelMapper
import org.demo.lowsproject.domain.model.TvShowModel
import org.junit.Test

class TvShowLocalDataSourceTest {
    private val database = mockk<AppDatabase>()
    private val dao = mockk<TvShowDao>()

    private val tvShowDataSource =
            TvShowLocalDataSourceImpl(
                    database,
                    TvShowDboToTvShowModelMapper()
            )

    @Test
    fun insertTvShow_returnSucessResult() = runBlocking {
        prepareScenario()

        val tvShowModel = TvShowModel(1, "", "")
        tvShowDataSource.insertTvShow(tvShowModel)

        coVerify(exactly = 1) {
            database.tvShowDao().insert(
                TvShowDbo(
                    id = tvShowModel.id,
                    name = tvShowModel.name,
                    image = tvShowModel.image
            )
            )
        }
    }

    @Test
    fun getById_returnSucessResult() = runBlocking {
        prepareScenario()

        val id = 1
        tvShowDataSource.getById(id)

        coVerify(exactly = 1) {
            database.tvShowDao().getById(id)
        }
    }

    @Test
    fun removeTvShow_returnSucessResult() = runBlocking {
        prepareScenario()

        val id = 1
        tvShowDataSource.removeTvShow(id)

        coVerify(exactly = 1) {
            database.tvShowDao().delete(id)
        }
    }

    @Test
    fun getAllTvShow_returnSucessResult() = runBlocking {
        prepareScenario()

        tvShowDataSource.getAllTvShow()

        coVerify(exactly = 1) {
            database.tvShowDao().getAll()
        }
    }

    private fun prepareScenario(tvDbo: TvShowDbo? = TvShowDbo(1, "", ""),
                                list: List<TvShowDbo> = listOf(TvShowDbo(1, "", ""))) {
        coEvery { database.tvShowDao().insert(any()) } just Runs
        coEvery { database.tvShowDao().getById(any()) } returns tvDbo
        coEvery { database.tvShowDao().delete(any()) } just Runs
        coEvery { database.tvShowDao().getAll() } returns list
    }
}