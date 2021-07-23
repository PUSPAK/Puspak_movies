package org.demo.lowsproject.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.demo.lowsproject.data.dbo.TvShowDbo

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tvshowdbo ORDER BY name")
    fun getAll(): List<TvShowDbo>

    @Query("SELECT * FROM tvshowdbo WHERE id == :id LIMIT 1")
    fun getById(id: Int): TvShowDbo?

    @Insert
    fun insert(vararg tvShow: TvShowDbo)

    @Query("DELETE FROM tvshowdbo WHERE id == :id ")
    fun delete(id: Int)
}