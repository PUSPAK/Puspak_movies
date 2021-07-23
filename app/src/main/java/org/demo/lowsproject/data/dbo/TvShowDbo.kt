package org.demo.lowsproject.data.dbo

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class TvShowDbo(
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "name") val name: String?
): Parcelable {
    @PrimaryKey(autoGenerate = true)
    var uuId: Int = 0
}
