package org.demo.lowsproject.data.mapper

import org.demo.lowsproject.data.dbo.TvShowDbo
import org.demo.lowsproject.domain.model.TvShowModel


class TvShowDboToTvShowModelMapper : BaseMapper<TvShowDbo, TvShowModel>() {
    override fun mapFrom(from: TvShowDbo) =
        TvShowModel(
            id = from.id ?: 0,
            name = from.name ?: "",
            image = from.image ?: ""
        )

}