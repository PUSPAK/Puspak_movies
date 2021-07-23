package org.demo.lowsproject.domain.model

data class TvShowDetailModel(
        val id: Int,
        val image: String,
        val name: String,
        val genres: List<String>,
        val schedule: ScheduleModel,
        val summary: String
)