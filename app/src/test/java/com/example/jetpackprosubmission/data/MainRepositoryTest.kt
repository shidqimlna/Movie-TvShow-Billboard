package com.example.jetpackprosubmission.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jetpackprosubmission.data.source.remote.RemoteDataSource
import com.example.jetpackprosubmission.utils.DataDummy
import com.example.jetpackprosubmission.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MainRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val mainRepository = FakeMainRepository(remote)

    private val movieListResponses = DataDummy.generateDummyMovies()
    private val movieDetailResponses = DataDummy.generateDummyMovies()[0]
    private val movieId = movieListResponses[0].id
    private val tvShowResponses = DataDummy.generateDummyTvShows()
    private val tvShowDetailResponses = DataDummy.generateDummyTvShows()[0]
    private val tvShowId = tvShowResponses[0].id

    @Test
    fun testGetMovieList() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieListResponses)
            null
        }.`when`(remote).getMovieList(any())
        val movieEntities = LiveDataTestUtil.getValue(mainRepository.getMovieList())
        verify(remote).getMovieList(any())
        assertNotNull(movieEntities)
        assertEquals(movieListResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun testGetMovieDetail() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMovieDetailCallback)
                .onDetailMovieReceived(movieDetailResponses)
            null
        }.`when`(remote).getMovieDetail(eq(movieId), any())

        val movieEntity =
            LiveDataTestUtil.getValue(mainRepository.getMovieDetail(movieId))

        verify(remote).getMovieDetail(eq(movieId), any())

        assertNotNull(movieEntity)
        assertEquals(movieDetailResponses.id, movieEntity.id)
        assertEquals(movieDetailResponses.title, movieEntity.title)
    }

    @Test
    fun testGetTvShowList() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
                .onAllTvShowsReceived(tvShowResponses)
            null
        }.`when`(remote).getTvShowList(any())
        val tvShowEntities = LiveDataTestUtil.getValue(mainRepository.getTvShowList())
        verify(remote).getTvShowList(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun testGetTvShowDetail() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvShowDetailCallback)
                .onDetailTvShowReceived(tvShowDetailResponses)
            null
        }.`when`(remote).getTvShowDetail(eq(tvShowId), any())

        val tvShowEntity =
            LiveDataTestUtil.getValue(mainRepository.getTvShowDetail(tvShowId))

        verify(remote).getTvShowDetail(eq(tvShowId), any())

        assertNotNull(tvShowEntity)
        assertEquals(tvShowDetailResponses.id, tvShowEntity.id)
        assertEquals(tvShowDetailResponses.name, tvShowEntity.name)
    }
}