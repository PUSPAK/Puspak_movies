package org.demo.lowsproject.domain.usecase

import kotlinx.coroutines.withContext
import org.demo.lowsproject.data.repository.TvShowRepository
import org.demo.lowsproject.data.utils.DispatcherProvider
import org.demo.lowsproject.domain.usecase.interfaces.RemoveFavoriteTvShowByIdUseCase

class RemoveFavoriteTvShowById(
    private val tvShowRepository: TvShowRepository,
    private val dispatchers: DispatcherProvider
) : RemoveFavoriteTvShowByIdUseCase {
    override suspend fun invoke(id: Int) =
        withContext(dispatchers.io()) { tvShowRepository.removeFavoriteTvShowById(id) }
}