package com.example.jetpackprosubmission.ui.movie

//import com.example.jetpackprosubmission.utils.DataDummy
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.jetpackprosubmission.data.MainRepository
import com.example.jetpackprosubmission.data.source.local.entity.MovieEntity
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
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mainRepository: MainRepository

    @Mock
    private lateinit var movieDetailObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var movieListObserver: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedListMovie: PagedList<MovieEntity>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MovieViewModel(mainRepository)
        viewModel.setMovieId(movieId)
    }

    @Test
    fun getMovieList() {
        val dummyMovies = Resource.success(pagedListMovie)
        `when`(dummyMovies.data?.size).thenReturn(10)
        `when`(dummyMovies.data?.get(0)).thenReturn(dummyMovie)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(mainRepository.getMovieList()).thenReturn(movies)
        val movieEntities = viewModel.getMovieList().value?.data
        verify(mainRepository).getMovieList()
        Assert.assertNotNull(movieEntities)
        assertEquals(dummyMovie, movieEntities?.get(0))
        Assert.assertEquals(10, movieEntities?.size)

        viewModel.getMovieList().observeForever(movieListObserver)
        verify(movieListObserver).onChanged(dummyMovies)
    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = Resource.success(dummyMovie)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyMovie

        `when`(mainRepository.getMovieDetail(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getMovieDetail().value
        verify(mainRepository).getMovieDetail(movieId)
        Assert.assertNotNull(movieEntity)
        assertEquals(dummyMovie.data?.id, movieEntity?.data?.id)
        assertEquals(dummyMovie.data?.posterPath, movieEntity?.data?.posterPath)
        assertEquals(dummyMovie.data?.title, movieEntity?.data?.title)
        assertEquals(dummyMovie.data?.overview, movieEntity?.data?.overview)
        assertEquals(dummyMovie.data?.runtime, movieEntity?.data?.runtime)
        assertEquals(dummyMovie.data?.releaseDate, movieEntity?.data?.releaseDate)
        assertEquals(dummyMovie.data?.voteAverage, movieEntity?.data?.voteAverage)

        viewModel.getMovieDetail().observeForever(movieDetailObserver)
        verify(movieDetailObserver).onChanged(dummyMovie)
    }
}