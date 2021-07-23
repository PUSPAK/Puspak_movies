package org.demo.lowsproject.data.mapper

import org.demo.lowsproject.data.dto.TvShowSearchDto
import org.demo.lowsproject.domain.model.TvShowModel


class TvShowSearchDtoToTvShowModelMapper : BaseMapper<TvShowSearchDto, TvShowModel>() {
    override fun mapFrom(from: TvShowSearchDto) =
        TvShowModel(
            id = from.show.id,
            name = from.show.name ?: "",
            image = from.show.image?.medium ?: ""
        )
}
