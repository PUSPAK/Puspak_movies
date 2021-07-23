package org.demo.lowsproject.data.mapper

import org.demo.lowsproject.data.dto.TvShowDto
import org.demo.lowsproject.domain.model.TvShowModel


class TvShowDtoToTvShowModelMapper : BaseMapper<TvShowDto, TvShowModel>() {
    override fun mapFrom(from: TvShowDto) =
        TvShowModel(
            id = from.id,
            name = from.name ?: "",
            image = from.image?.medium ?: ""
        )

}