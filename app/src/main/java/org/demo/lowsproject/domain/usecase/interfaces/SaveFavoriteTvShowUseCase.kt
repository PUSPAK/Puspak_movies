package org.demo.lowsproject.domain.usecase.interfaces

import org.demo.lowsproject.domain.model.TvShowModel


interface SaveFavoriteTvShowUseCase {
    suspend operator fun invoke(tvShow: TvShowModel)
}
