package org.demo.lowsproject.data.mapper




import junit.framework.TestCase.assertEquals
import org.demo.lowsproject.data.dto.EpisodeDto
import org.demo.lowsproject.data.dto.ImageDto
import org.junit.Assert
import org.junit.Test

class EpisodeDtoToEpisodeModelMapperTest {

    @Test
    fun mapFromDto_nullvalues_returnModel() {
        val episodeDto = EpisodeDto(1, 1, "", 0, "", ImageDto(""))

        val mapper = EpisodeDtoToEpisodeModelMapper()

        val episodeModel = mapper.mapFrom(episodeDto)

        assertEquals(episodeDto.id, episodeModel.id)
        assertEquals(episodeDto.season, episodeModel.season)
        assertEquals(episodeDto.name, episodeModel.name)
        assertEquals(episodeDto.number, episodeModel.number)
        assertEquals(episodeDto.summary, episodeModel.summary)
        assertEquals(episodeDto.image?.medium, episodeModel.image)
    }
}