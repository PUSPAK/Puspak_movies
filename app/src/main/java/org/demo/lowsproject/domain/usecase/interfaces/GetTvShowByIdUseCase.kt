package org.demo.lowsproject.domain.usecase.interfaces

import org.demo.lowsproject.data.Resource
import org.demo.lowsproject.domain.model.TvShowDetailModel


interface GetTvShowByIdUseCase {
    suspend operator fun invoke(id: Int): Resource<TvShowDetailModel>
}