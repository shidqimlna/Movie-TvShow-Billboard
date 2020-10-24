package com.example.jetpackprosubmission.data.viewmodel

import com.example.jetpackprosubmission.util.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val tvShowId = dummyTvShow.id

    @Before
    fun setUp() {
        mainViewModel = MainViewModel()
        mainViewModel.setMovieList()
        mainViewModel.setMovieDetail(movieId)
        mainViewModel.setTvShowList()
        mainViewModel.setTvShowDetail(tvShowId)
    }

    @Test
    fun testGetMovieList() {
        mainViewModel.setMovieList()
        val movieEntities = mainViewModel.getMovieList()
        assertNotNull(movieEntities)
        assertEquals(20, movieEntities.value?.size)
    }

    @Test
    fun testGetTvShowList() {
        mainViewModel.setTvShowList()
        val tvShowEntities = mainViewModel.getTvShowList()
        assertNotNull(tvShowEntities)
        assertEquals(20, tvShowEntities.value?.size)
    }

    @Test
    fun testGetMovieDetail() {
        mainViewModel.setMovieDetail(dummyMovie.id)
        val movieEntity = mainViewModel.getMovieDetail()
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.poster_path, movieEntity.poster_path)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.overview, movieEntity.overview)
        assertEquals(dummyMovie.runtime, movieEntity.runtime)
        assertEquals(dummyMovie.release_date, movieEntity.release_date)
        assertEquals(dummyMovie.release_date, movieEntity.release_date)
    }

    @Test
    fun testGetTvShowDetail() {
        mainViewModel.setTvShowDetail(dummyTvShow.id)
        val tvShowEntity = mainViewModel.getTvShowDetail()
        assertNotNull(tvShowEntity)
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