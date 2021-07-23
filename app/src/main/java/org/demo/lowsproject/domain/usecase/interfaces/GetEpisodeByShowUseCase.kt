package org.demo.lowsproject.domain.usecase.interfaces

import org.demo.lowsproject.data.Resource
import org.demo.lowsproject.domain.model.EpisodeBySeasonModel


interface GetEpisodeByShowUseCase {
    suspend operator fun invoke(id: Int): Resource<EpisodeBySeasonModel>
}