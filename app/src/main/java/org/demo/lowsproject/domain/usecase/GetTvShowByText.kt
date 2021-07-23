package org.demo.lowsproject.domain.usecase

import org.demo.lowsproject.data.repository.TvShowRepository
import org.demo.lowsproject.domain.usecase.interfaces.GetTvShowByTextUseCase


class GetTvShowByText(
    private val tvShowRepository: TvShowRepository
) : GetTvShowByTextUseCase {
    override suspend fun invoke(searchText: String) = tvShowRepository.getTvShowsByText(searchText)
}