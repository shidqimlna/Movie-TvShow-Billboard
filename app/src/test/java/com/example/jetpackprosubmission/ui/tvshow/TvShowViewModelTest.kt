package com.example.jetpackprosubmission.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.jetpackprosubmission.data.MainRepository
import com.example.jetpackprosubmission.data.source.local.entity.TvShowEntity
import com.example.jetpackprosubmission.utils.DataDummy
import com.example.jetpackprosubmission.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel
    private val dummyTvShow = DataDummy.generateDummyTvShowDetail()
    private val tvShowId = dummyTvShow.id

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mainRepository: MainRepository

    @Mock
    private lateinit var tvShowDetailObserver: Observer<Resource<TvShowEntity>>

    @Mock
    private lateinit var tvShowListObserver: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedListTvShow: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = TvShowViewModel(mainRepository)
        viewModel.setTvShowId(tvShowId)
    }

    @Test
    fun getTvShowList() {
        val dummyTvShows = Resource.success(pagedListTvShow)
        `when`(dummyTvShows.data?.size).thenReturn(10)
        `when`(dummyTvShows.data?.get(0)).thenReturn(dummyTvShow)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = dummyTvShows

        `when`(mainRepository.getTvShowList()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getTvShowList().value?.data
        verify(mainRepository).getTvShowList()
        Assert.assertNotNull(tvShowEntities)
        assertEquals(dummyTvShow, tvShowEntities?.get(0))
        Assert.assertEquals(10, tvShowEntities?.size)

        viewModel.getTvShowList().observeForever(tvShowListObserver)
        verify(tvShowListObserver).onChanged(dummyTvShows)
    }

    @Test
    fun getTvShowDetail() {
        val dummyTvShow = Resource.success(dummyTvShow)
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyTvShow

        `when`(mainRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)

        viewModel.getTvShowDetail().observeForever(tvShowDetailObserver)

        verify(tvShowDetailObserver).onChanged(dummyTvShow)
    }
}