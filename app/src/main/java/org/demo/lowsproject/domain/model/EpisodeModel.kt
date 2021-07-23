package org.demo.lowsproject.domain.model

data class EpisodeModel(
        val id: Int,
        val season: Int,
        val name: String,
        val number: Int,
        val summary: String,
        val image: String
)
