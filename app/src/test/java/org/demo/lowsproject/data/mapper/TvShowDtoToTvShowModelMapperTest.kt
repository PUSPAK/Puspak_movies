package org.demo.lowsproject.data.mapper


import org.demo.lowsproject.data.dto.ImageDto
import org.demo.lowsproject.data.dto.TvShowDto
import org.demo.lowsproject.data.mapper.TvShowDtoToTvShowModelMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class TvShowDtoToTvShowModelMapperTest {

    @Test
    fun mapFromDto_nullValues_returnModel() {
        val tvShowDto = TvShowDto(1, null, null)

        val mapper = TvShowDtoToTvShowModelMapper()

        val tvShowModel = mapper.mapFrom(tvShowDto)

        assertEquals(tvShowDto.id, tvShowModel.id)
        assertEquals("", tvShowModel.name)
        assertEquals("", tvShowModel.image)

    }

    @Test
    fun mapFromDto_returnModel() {
        val tvShowDto = TvShowDto(1, "test", ImageDto("imageTest"))

        val mapper = TvShowDtoToTvShowModelMapper()

        val tvShowModel = mapper.mapFrom(tvShowDto)

        assertEquals(tvShowDto.id, tvShowModel.id)
        assertEquals("test", tvShowModel.name)
        assertEquals("imageTest", tvShowModel.image)

    }
}