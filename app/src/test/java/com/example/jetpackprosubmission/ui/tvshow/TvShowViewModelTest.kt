package com.example.jetpackprosubmission.ui.tvshow

import com.example.jetpackprosubmission.util.DataDummy
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val tvShowId = dummyTvShow.id

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getTvShows() {
        val entities = viewModel.getTvShows()
        Assert.assertNotNull(entities)
        Assert.assertEquals(10, entities.size.toLong())
    }

    @Test
    fun getTvShow() {
        viewModel.setSelectedTvShow(dummyTvShow.id)
        val tvShowEntity = viewModel.getTvShow()
        Assert.assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.id, tvShowEntity.id)
        assertEquals(dummyTvShow.poster_path, tvShowEntity.poster_path)
        assertEquals(dummyTvShow.name, tvShowEntity.name)
        assertEquals(dummyTvShow.overview, tvShowEntity.overview)
        assertEquals(dummyTvShow.first_air_date, tvShowEntity.first_air_date)
        assertEquals(dummyTvShow.last_air_date, tvShowEntity.last_air_date)
        assertEquals(dummyTvShow.vote_average, tvShowEntity.vote_average)
        assertEquals(dummyTvShow.number_of_episodes, tvShowEntity.number_of_episodes)
        assertEquals(dummyTvShow.number_of_seasons, tvShowEntity.number_of_seasons)
    }
}