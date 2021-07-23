package org.demo.lowsproject.domain.usecase

import org.demo.lowsproject.data.repository.TvShowRepository
import org.demo.lowsproject.domain.usecase.interfaces.GetTvShowByPageUseCase


class GetTvShowByPage(
    private val tvShowRepository: TvShowRepository
) : GetTvShowByPageUseCase {
    override suspend fun invoke(page: Int) = tvShowRepository.getTvShowsByPage(page)

}