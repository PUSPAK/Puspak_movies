package org.demo.lowsproject.domain.usecase

import kotlinx.coroutines.withContext
import org.demo.lowsproject.data.repository.TvShowRepository
import org.demo.lowsproject.data.utils.DispatcherProvider
import org.demo.lowsproject.domain.usecase.interfaces.GetFavoriteListUseCase

class GetFavoriteList(
    private val tvShowRepository: TvShowRepository,
    private val dispatchers: DispatcherProvider
) : GetFavoriteListUseCase {
    override suspend fun invoke() =
        withContext(dispatchers.io()) { tvShowRepository.getAllFavoriteTvShow() }
}