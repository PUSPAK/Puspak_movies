package org.demo.lowsproject.domain.usecase.interfaces

import org.demo.lowsproject.data.Resource
import org.demo.lowsproject.domain.model.TvShowModel


interface GetTvShowByTextUseCase {
    suspend operator fun invoke(searchText: String): Resource<List<TvShowModel>>
}
