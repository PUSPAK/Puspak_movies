package org.demo.lowsproject.data.mapper

import org.demo.lowsproject.data.dto.TvShowDetailDto
import org.demo.lowsproject.domain.model.ScheduleModel
import org.demo.lowsproject.domain.model.TvShowDetailModel


class TvShowDetailDtoToTvShowDetailModelMapper : BaseMapper<TvShowDetailDto, TvShowDetailModel>() {
    override fun mapFrom(from: TvShowDetailDto) =
            TvShowDetailModel(
                    id = from.id,
                    name = from.name ?: "",
                    image = from.image?.medium ?: "",
                    genres = from.genres ?: emptyList(),
                    summary = from.summary ?: "",
                    schedule = ScheduleModel(
                            time = from.schedule?.time ?: "",
                            days = from.schedule?.days ?: emptyList()
                    )
            )
}