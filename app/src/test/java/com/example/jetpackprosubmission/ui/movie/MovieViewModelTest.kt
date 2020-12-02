package com.example.jetpackprosubmission.ui.movie
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.Observer
//import androidx.paging.PagedList
//import com.example.jetpackprosubmission.data.MainRepository
//import com.example.jetpackprosubmission.data.source.local.entity.MovieEntity
//import com.example.jetpackprosubmission.data.source.remote.RemoteRepository
////import com.example.jetpackprosubmission.utils.DataDummy
//import junit.framework.Assert.assertEquals
//import org.junit.Assert
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito
//import org.mockito.MockitoAnnotations
//import org.mockito.junit.MockitoJUnitRunner
//
//@RunWith(MockitoJUnitRunner::class)
//class MovieViewModelTest {
////    private lateinit var viewModel: MovieViewModel
////    private val dummyMovie = DataDummy.generateDummyMovies()[0]
////    private val movieId = dummyMovie?.id
//
//    @Rule
//    @JvmField
//    val instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @Mock
//    private lateinit var mainRepository: MainRepository
//
//    @Mock
//    private lateinit var movieDetailObserver: Observer<MovieEntity>
//
//    @Mock
//    private lateinit var movieListObserver: Observer<PagedList<MovieEntity>>
//
//    @Before
//    fun setUp() {
//        MockitoAnnotations.initMocks(this)
////        viewModel = MovieViewModel(mainRepository)
////        viewModel.setMovie(movieId)
//    }
//
//    @Test
//    fun getMovieList() {
//        val dummyContent: MutableLiveData<PagedList<MovieEntity>> = MutableLiveData()
//        val pagedList: PagedList<*> = Mockito.mock(PagedList::class.java)
//        dummyContent.value = pagedList as PagedList<MovieEntity>
//
////        val dummyMovies = DataDummy.generateDummyMovies()
////        val movies = MutableLiveData<PagedList<MovieEntity>>()
////        movies.value = dummyMovies
//
////        Mockito.`when`(repository.getAllMovies()).thenReturn(dummyContent)
////        Mockito.verify(repository).getAllMovies()
//
//        Mockito.`when`(mainRepository.getMovieList()).thenReturn(dummyContent)
//        Mockito.verify(mainRepository).getMovieList()
////        val movieEntities = viewModel.getMovieList().value
////        Assert.assertNotNull(movieEntities)
////        Assert.assertEquals(10, movieEntities?.size)
//
////        viewModel.getMovieList().observeForever(movieListObserver)
////        Mockito.verify(movieListObserver).onChanged(dummyContent)
//    }
//
////    @Test
////    fun getMovieDetail() {
////        val movie = MutableLiveData<MovieEntity>()
////        movie.value = dummyMovie
////
////        Mockito.`when`(mainRepository.getMovieDetail(movieId)).thenReturn(movie)
////        val movieEntity = viewModel.getMovieDetail().value as MovieEntity
////        Mockito.verify(mainRepository).getMovieDetail(movieId)
////        Assert.assertNotNull(movieEntity)
////        assertEquals(dummyMovie?.id, movieEntity.id)
////        assertEquals(dummyMovie?.poster_path, movieEntity.poster_path)
////        assertEquals(dummyMovie?.title, movieEntity.title)
////        assertEquals(dummyMovie?.overview, movieEntity.overview)
////        assertEquals(dummyMovie?.runtime, movieEntity.runtime)
////        assertEquals(dummyMovie?.release_date, movieEntity.release_date)
////        assertEquals(dummyMovie?.release_date, movieEntity.release_date)
////
////        viewModel.getMovieDetail().observeForever(movieDetailObserver)
////        Mockito.verify(movieDetailObserver).onChanged(dummyMovie)
////    }
//}