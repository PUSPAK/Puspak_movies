package org.demo.lowsproject.domain.usecase

import kotlinx.coroutines.withContext
import org.demo.lowsproject.data.repository.TvShowRepository
import org.demo.lowsproject.data.utils.DispatcherProvider
import org.demo.lowsproject.domain.usecase.interfaces.GetFavoriteTvShowByIdUseCase

class GetFavoriteTvShowById(
    private val tvShowRepository: TvShowRepository,
    private val dispatchers: DispatcherProvider
) : GetFavoriteTvShowByIdUseCase {
    override suspend fun invoke(id: Int) =
        withContext(dispatchers.io()) { tvShowRepository.getFavoriteTvShowById(id) }
}