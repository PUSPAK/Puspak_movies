package org.demo.lowsproject.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class EpisodeItemResult(
    val id: Int = 0,
    val name: String,
    val number: Int,
    val season: Int,
    val summary: String,
    val image: String,
    val resultType: ResultType = ResultType.EPISODE
) : Parcelable

@Parcelize
class SeasonHeader(val seasonNumber: Int) : EpisodeItemResult(0, "", 0, 0, "", "", ResultType.SEASON_HEADER)

