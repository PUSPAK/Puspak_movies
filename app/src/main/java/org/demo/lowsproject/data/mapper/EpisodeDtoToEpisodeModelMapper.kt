package org.demo.lowsproject.data.mapper

import org.demo.lowsproject.data.dto.EpisodeDto
import org.demo.lowsproject.domain.model.EpisodeModel


class EpisodeDtoToEpisodeModelMapper : BaseMapper<EpisodeDto, EpisodeModel>() {
    override fun mapFrom(from: EpisodeDto) = EpisodeModel(
        id = from.id,
        season = from.season ?: 0,
        name = from.name ?: "",
        number = from.number ?: 0,
        summary = from.summary ?: "",
        image = from.image?.medium ?: ""
    )
}