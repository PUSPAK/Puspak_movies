package org.demo.lowsproject.domain.usecase.interfaces

import org.demo.lowsproject.domain.model.TvShowModel


interface GetFavoriteListUseCase {
    suspend operator fun invoke(): List<TvShowModel>?
}