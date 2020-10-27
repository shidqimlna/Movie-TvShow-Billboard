package com.example.jetpackprosubmission.ui.movie

import com.example.jetpackprosubmission.util.DataDummy
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovies() {
        val entities = viewModel.getMovies()
        Assert.assertNotNull(entities)
        Assert.assertEquals(10, entities.size.toLong())
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedMovie(dummyMovie.id)
        val movieEntity = viewModel.getMovie()
        Assert.assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.poster_path, movieEntity.poster_path)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.overview, movieEntity.overview)
        assertEquals(dummyMovie.runtime, movieEntity.runtime)
        assertEquals(dummyMovie.release_date, movieEntity.release_date)
        assertEquals(dummyMovie.release_date, movieEntity.release_date)
    }
}