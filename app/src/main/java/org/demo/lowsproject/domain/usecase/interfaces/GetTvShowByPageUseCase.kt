package org.demo.lowsproject.domain.usecase.interfaces

import org.demo.lowsproject.data.Resource
import org.demo.lowsproject.domain.model.TvShowModel


interface GetTvShowByPageUseCase {
    suspend operator fun invoke(page: Int = 0): Resource<List<TvShowModel>>
}