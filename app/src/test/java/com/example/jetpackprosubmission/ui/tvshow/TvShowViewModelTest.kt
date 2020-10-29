package com.example.jetpackprosubmission.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.jetpackprosubmission.data.MainRepository
import com.example.jetpackprosubmission.data.source.local.entity.TvShowEntity
import com.example.jetpackprosubmission.utils.DataDummy
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val tvShowId = dummyTvShow.id

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mainRepository: MainRepository

    @Mock
    private lateinit var tvShowDetailObserver: Observer<TvShowEntity>

    @Mock
    private lateinit var tvShowListObserver: Observer<List<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(mainRepository)
        viewModel.setTvShow(tvShowId)
    }

    @Test
    fun getTvShowList() {
        val dummyTvShows = DataDummy.generateDummyTvShows()
        val tvShow = MutableLiveData<List<TvShowEntity>>()
        tvShow.value = dummyTvShows

        Mockito.`when`(mainRepository.getTvShowList()).thenReturn(tvShow)
        val tvShowEntities = viewModel.getTvShowList().value
        Mockito.verify(mainRepository).getTvShowList()
        Assert.assertNotNull(tvShowEntities)
        Assert.assertEquals(10, tvShowEntities?.size)

        viewModel.getTvShowList().observeForever(tvShowListObserver)
        Mockito.verify(tvShowListObserver).onChanged(dummyTvShows)
    }

    @Test
    fun getTvShowDetail() {
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = dummyTvShow

        Mockito.`when`(mainRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getTvShowDetail().value as TvShowEntity
        Mockito.verify(mainRepository).getTvShowDetail(tvShowId)
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

        viewModel.getTvShowDetail().observeForever(tvShowDetailObserver)
        Mockito.verify(tvShowDetailObserver).onChanged(dummyTvShow)
    }
}