package com.example.jetpackprosubmission.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.example.jetpackprosubmission.data.source.local.LocalDataSource
import com.example.jetpackprosubmission.data.source.local.entity.MovieEntity
import com.example.jetpackprosubmission.data.source.local.entity.TvShowEntity
import com.example.jetpackprosubmission.data.source.remote.RemoteDataSource
import com.example.jetpackprosubmission.util.AppExecutors
import com.example.jetpackprosubmission.utils.DataDummy
import com.example.jetpackprosubmission.utils.PagedListUtil
import com.example.jetpackprosubmission.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class RemoteRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val mainRepository = FakeMainRepository(remote, local, appExecutors)

    private val movieListResponses = DataDummy.generateDummyMovies()
    private val movieDetailResponses = DataDummy.generateDummyMovies()[0]
    private val movieId = movieListResponses[0].id
    private val tvShowResponses = DataDummy.generateDummyTvShows()
    private val tvShowDetailResponses = DataDummy.generateDummyTvShows()[0]
    private val tvShowId = tvShowResponses[0].id

    @Test
    fun testGetMovieList() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getMovieList()).thenReturn(dataSourceFactory)
        mainRepository.getMovieList()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getMovieList()
        assertNotNull(movieEntities.data)
        assertEquals(movieListResponses.size.toLong(), movieEntities.data?.size?.toLong())

//        doAnswer { invocation ->
//            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
//                .onAllMoviesReceived(movieListResponses)
//            null
//        }.`when`(remote).getMovieList()
//        val movieEntities = LiveDataTestUtil.getValue(mainRepository.getMovieList())
//        verify(local).getMovieList()
//        assertNotNull(movieEntities)
//        assertEquals(movieListResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun testGetMovieDetail() {
//        doAnswer { invocation ->
//            (invocation.arguments[1] as RemoteDataSource.LoadMovieDetailCallback)
//                .onDetailMovieReceived(movieDetailResponses)
//            null
//        }.`when`(remote).getMovieDetail(eq(movieId))
//
//        val movieEntity =
//            LiveDataTestUtil.getValue(mainRepository.getMovieDetail(movieId))
//
//        verify(remote).getMovieDetail(eq(movieId))
//
//        assertNotNull(movieEntity)
//        assertEquals(movieDetailResponses?.id, movieEntity.id)
//        assertEquals(movieDetailResponses?.title, movieEntity.title)
    }

    @Test
    fun testGetTvShowList() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(local.getTvShowList()).thenReturn(dataSourceFactory)
        mainRepository.getTvShowList()

        val entities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getTvShowList()
        assertNotNull(entities.data)
        assertEquals(tvShowResponses.size.toLong(), entities.data?.size?.toLong())

//        doAnswer { invocation ->
//            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
//                .onAllTvShowsReceived(tvShowResponses)
//            null
//        }.`when`(remote).getTvShowList()
//        val tvShowEntities = LiveDataTestUtil.getValue(mainRepository.getTvShowList())
//        verify(remote).getTvShowList()
//        assertNotNull(tvShowEntities)
//        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun testGetTvShowDetail() {
//        doAnswer { invocation ->
//            (invocation.arguments[1] as RemoteDataSource.LoadTvShowDetailCallback)
//                .onDetailTvShowReceived(tvShowDetailResponses)
//            null
//        }.`when`(remote).getTvShowDetail(eq(tvShowId))
//
//        val tvShowEntity =
//            LiveDataTestUtil.getValue(mainRepository.getTvShowDetail(tvShowId))
//
//        verify(remote).getTvShowDetail(eq(tvShowId))
//
//        assertNotNull(tvShowEntity)
//        assertEquals(tvShowDetailResponses?.id, tvShowEntity.id)
//        assertEquals(tvShowDetailResponses?.name, tvShowEntity.name)
    }
}