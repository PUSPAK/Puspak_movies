package org.demo.lowsproject.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageDto(@SerializedName("medium") val medium: String) : Parcelable
