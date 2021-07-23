package org.demo.lowsproject.domain.usecase

import kotlinx.coroutines.withContext
import org.demo.lowsproject.data.repository.TvShowRepository
import org.demo.lowsproject.data.utils.DispatcherProvider
import org.demo.lowsproject.domain.model.TvShowModel
import org.demo.lowsproject.domain.usecase.interfaces.SaveFavoriteTvShowUseCase

class SaveFavoriteTvShow(
    private val tvShowRepository: TvShowRepository,
    private val dispatchers: DispatcherProvider
) : SaveFavoriteTvShowUseCase {
    override suspend fun invoke(tvShow: TvShowModel) =
        withContext(dispatchers.io()) { tvShowRepository.saveFavoriteTvShow(tvShow) }

}