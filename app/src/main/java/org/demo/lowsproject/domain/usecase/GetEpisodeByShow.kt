package org.demo.lowsproject.domain.usecase

import kotlinx.coroutines.withContext
import org.demo.lowsproject.data.Resource
import org.demo.lowsproject.data.Status
import org.demo.lowsproject.data.repository.EpisodeRepository
import org.demo.lowsproject.data.utils.DispatcherProvider
import org.demo.lowsproject.domain.model.EpisodeBySeasonModel
import org.demo.lowsproject.domain.model.EpisodeItemResult
import org.demo.lowsproject.domain.model.SeasonHeader
import org.demo.lowsproject.domain.usecase.interfaces.GetEpisodeByShowUseCase

class GetEpisodeByShow(
    private val episodeRepository: EpisodeRepository,
    private val dispatchers: DispatcherProvider
) : GetEpisodeByShowUseCase {
    override suspend fun invoke(id: Int): Resource<EpisodeBySeasonModel> =
        withContext(dispatchers.io()) {
            val tvEpisodesByShowId = episodeRepository.getTvEpisodesByShowId(id)
            val episodeBySeasonUiModel = EpisodeBySeasonModel()

            if (tvEpisodesByShowId.status == Status.SUCCESS) {
                tvEpisodesByShowId.data?.let {
                    val data = tvEpisodesByShowId.data.groupBy { it.season }
                    for (k in data.keys) {
                        val elements = data[k]?.map {
                            EpisodeItemResult(
                                id = it.id,
                                name = it.name,
                                number = it.number,
                                season = it.season,
                                summary = it.summary,
                                image = it.image
                            )
                        } ?: emptyList()

                        episodeBySeasonUiModel.list.add(SeasonHeader(k))
                        episodeBySeasonUiModel.list.addAll(elements)
                    }
                }

                Resource(
                    status = tvEpisodesByShowId.status,
                    data = episodeBySeasonUiModel,
                    message = tvEpisodesByShowId.message
                )
            } else {
                Resource(
                    status = tvEpisodesByShowId.status,
                    data = episodeBySeasonUiModel,
                    message = tvEpisodesByShowId.message
                )
            }
        }
}