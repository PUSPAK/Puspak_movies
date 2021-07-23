package org.demo.lowsproject.domain.usecase

import org.demo.lowsproject.data.repository.TvShowRepository
import org.demo.lowsproject.domain.usecase.interfaces.GetTvShowByIdUseCase


class GetTvShowById(
        private val tvShowRepository: TvShowRepository
) : GetTvShowByIdUseCase {
    override suspend fun invoke(id: Int) = tvShowRepository.getTvShowById(id)
}