package com.example.jetpackprosubmission.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.jetpackprosubmission.data.source.local.LocalDataSource
import com.example.jetpackprosubmission.data.source.local.entity.MovieEntity
import com.example.jetpackprosubmission.data.source.local.entity.TvShowEntity
import com.example.jetpackprosubmission.data.source.remote.RemoteDataSource
import com.example.jetpackprosubmission.util.AppExecutors
import com.example.jetpackprosubmission.utils.DataDummy
import com.example.jetpackprosubmission.utils.LiveDataTestUtil
import com.example.jetpackprosubmission.utils.PagedListUtil
import com.example.jetpackprosubmission.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class RemoteRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val mainRepository = FakeMainRepository(remote, local, appExecutors)

    private val movieListResponses = DataDummy.generateDummyMovies()
    private val movieDetailResponses = DataDummy.generateDummyMovieDetail()
    private val movieId = movieDetailResponses.id

    private val tvShowListResponses = DataDummy.generateDummyTvShows()
    private val tvShowDetailResponses = DataDummy.generateDummyTvShowDetail()
    private val tvShowId = tvShowDetailResponses.id

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
    }

    @Test
    fun testGetMovieDetail() {
        val dummyEntity = MutableLiveData<MovieEntity>()
        dummyEntity.value = DataDummy.generateDummyMovieDetail()

        Mockito.`when`(local.getMovieDetail(movieId)).thenReturn(dummyEntity)
        val movieEntity = LiveDataTestUtil.getValue(mainRepository.getMovieDetail(movieId))
        verify(local).getMovieDetail(movieId)

        assertNotNull(movieEntity.data)
        assertNotNull(movieEntity.data?.title)
        assertEquals(movieDetailResponses.id, movieEntity.data?.id)
        assertEquals(movieDetailResponses.title, movieEntity.data?.title)
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
        assertEquals(tvShowListResponses.size.toLong(), entities.data?.size?.toLong())
    }

    @Test
    fun testGetTvShowDetail() {
        val dummyEntity = MutableLiveData<TvShowEntity>()
        dummyEntity.value = DataDummy.generateDummyTvShowDetail()

        Mockito.`when`(local.getTvShowDetail(tvShowId)).thenReturn(dummyEntity)
        val tvShowEntity = LiveDataTestUtil.getValue(mainRepository.getTvShowDetail(tvShowId))
        verify(local).getTvShowDetail(tvShowId)

        assertNotNull(tvShowEntity.data)
        assertNotNull(tvShowEntity.data?.name)
        assertEquals(tvShowDetailResponses.id, tvShowEntity.data?.id)
        assertEquals(tvShowDetailResponses.name, tvShowEntity.data?.name)
    }
}