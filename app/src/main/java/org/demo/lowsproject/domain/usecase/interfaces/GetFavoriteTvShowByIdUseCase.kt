package org.demo.lowsproject.domain.usecase.interfaces

import org.demo.lowsproject.domain.model.TvShowModel


interface GetFavoriteTvShowByIdUseCase {
    suspend operator fun invoke(id: Int): TvShowModel?
}
