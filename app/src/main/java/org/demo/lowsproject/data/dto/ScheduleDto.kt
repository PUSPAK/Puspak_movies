package org.demo.lowsproject.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ScheduleDto(
        @SerializedName("time") val time: String,
        @SerializedName("days") val days: List<String>
) : Parcelable