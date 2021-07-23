package com.otero.tvmazeapp.data.mapper

import org.demo.lowsproject.data.dto.ImageDto
import org.demo.lowsproject.data.dto.ScheduleDto
import org.demo.lowsproject.data.dto.TvShowDetailDto
import org.demo.lowsproject.data.mapper.TvShowDetailDtoToTvShowDetailModelMapper
import org.junit.Assert
import org.junit.Test

class TvShowDetailDtoToTvShowDetailModelMapperTest {
    @Test
    fun mapFromDto_nullvalues_returnModel() {
        val tvShowDetailDto = TvShowDetailDto(1, null, null, null, null,
                null)

        val mapper = TvShowDetailDtoToTvShowDetailModelMapper()

        val tvShowDetailModel = mapper.mapFrom(tvShowDetailDto)

        Assert.assertEquals(tvShowDetailDto.id, tvShowDetailModel.id)
        Assert.assertEquals("", tvShowDetailModel.name)
        Assert.assertEquals("", tvShowDetailModel.image)
        Assert.assertEquals(emptyList<String>(), tvShowDetailModel.genres)
        Assert.assertEquals("", tvShowDetailModel.summary)
        Assert.assertEquals("", tvShowDetailModel.schedule.time)
        Assert.assertEquals(emptyList<String>(), tvShowDetailModel.schedule.days)

    }

    @Test
    fun mapFromDto_returnModel() {
        val tvShowDetailDto = TvShowDetailDto(1, "test", ImageDto("test"),
        listOf("Genre1", "Genre2"), "Summary", ScheduleDto("22:00", listOf("Sunday"))
        )

        val mapper = TvShowDetailDtoToTvShowDetailModelMapper()

        val tvShowDetailModel = mapper.mapFrom(tvShowDetailDto)

        Assert.assertEquals(tvShowDetailDto.id, tvShowDetailModel.id)
        Assert.assertEquals("test", tvShowDetailModel.name)
        Assert.assertEquals(tvShowDetailDto.image?.medium, tvShowDetailModel.image)
        Assert.assertEquals(listOf("Genre1", "Genre2"), tvShowDetailModel.genres)
        Assert.assertEquals("Summary", tvShowDetailModel.summary)
        Assert.assertEquals("22:00", tvShowDetailModel.schedule.time)
        Assert.assertEquals(listOf("Sunday"), tvShowDetailModel.schedule.days)

    }
}