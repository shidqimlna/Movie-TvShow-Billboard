package com.example.jetpackprosubmission.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.jetpackprosubmission.data.source.NetworkBoundResource
import com.example.jetpackprosubmission.data.source.local.LocalDataSource
import com.example.jetpackprosubmission.data.source.local.entity.FavoriteMovieEntity
import com.example.jetpackprosubmission.data.source.local.entity.FavoriteTvShowEntity
import com.example.jetpackprosubmission.data.source.local.entity.MovieEntity
import com.example.jetpackprosubmission.data.source.local.entity.TvShowEntity
import com.example.jetpackprosubmission.data.source.remote.ApiResponse
import com.example.jetpackprosubmission.data.source.remote.RemoteDataSource
import com.example.jetpackprosubmission.data.source.remote.response.MovieApiItem
import com.example.jetpackprosubmission.data.source.remote.response.TvShowApiItem
import com.example.jetpackprosubmission.util.AppExecutors
import com.example.jetpackprosubmission.util.Helper.getGenres
import com.example.jetpackprosubmission.vo.Resource

class MainRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MainDataSource {

    companion object {
        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MainRepository {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = MainRepository(remoteData, localData, appExecutors)
                    }
                }
            }
            return instance as MainRepository
        }
    }

    override fun getMovieList(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieApiItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovieList(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<MovieApiItem>>> =
                remoteDataSource.getMovieList()

            public override fun saveCallResult(data: List<MovieApiItem>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
//                    Log.i("REPOS", "REPOS RESPONSE SAVECALL:" + response.title)
                    val movie = MovieEntity(
                        id = response.id,
                        title = response.title,
                        posterPath = response.posterPath,
                        voteAverage = response.voteAverage,
                        overview = response.overview
                    )
                    movieList.add(movie)
                }
//                Log.i("REPOS", "REPOS INSERT LOCAL:" + movieList.get(0).title)
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getMovieDetail(movieId: String?): LiveData<Resource<MovieEntity>> {
        var codeNull = 0
        return object : NetworkBoundResource<MovieEntity, MovieApiItem>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getMovieDetail(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean {
                if (data == null)
                    codeNull = 1
                else if (data.genres == null || data.runtime == null)
                    codeNull = 2
                return data?.genres == null || data.runtime == null
            }

            override fun createCall(): LiveData<ApiResponse<MovieApiItem>> =
                remoteDataSource.getMovieDetail(movieId)

            override fun saveCallResult(data: MovieApiItem) {
                val movie = MovieEntity(
                    id = data.id,
                    title = data.title,
                    overview = data.overview,
                    releaseDate = data.releaseDate,
                    runtime = data.runtime,
                    voteAverage = data.voteAverage,
                    genres = getGenres(data.genres),
                    posterPath = data.posterPath
                )

                if (codeNull == 1)
                    localDataSource.insertMovie(movie)
                else if (codeNull == 2)
                    localDataSource.setDetailMovie(movie)
            }
        }.asLiveData()
    }

    override fun getTvShowList(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowApiItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvShowList(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowApiItem>>> =
                remoteDataSource.getTvShowList()

            override fun saveCallResult(data: List<TvShowApiItem>) {
                val tvShows = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        id = response.id,
                        name = response.name,
                        overview = response.overview,
                        voteAverage = response.voteAverage,
                        posterPath = response.posterPath
                    )
                    tvShows.add(tvShow)
                }

                localDataSource.insertTvShows(tvShows)
            }

        }.asLiveData()
    }

    override fun getTvShowDetail(tvShowId: String?): LiveData<Resource<TvShowEntity>> {
        var codeNull = 0
        return object : NetworkBoundResource<TvShowEntity, TvShowApiItem>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getTvShowDetail(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean {
                if (data == null)
                    codeNull = 1
                else if (data.genres == null || data.numberOfEpisodes == null)
                    codeNull = 2
                return data?.genres == null || data.numberOfEpisodes == null
            }

            override fun createCall(): LiveData<ApiResponse<TvShowApiItem>> =
                remoteDataSource.getTvShowDetail(tvShowId)

            override fun saveCallResult(data: TvShowApiItem) {
                val tvShow = TvShowEntity(
                    id = data.id,
                    name = data.name,
                    overview = data.overview,
                    firstAirDate = data.firstAirDate,
                    lastAirDate = data.lastAirDate,
                    numberOfEpisodes = data.numberOfEpisodes,
                    numberOfSeasons = data.numberOfSeasons,
                    voteAverage = data.voteAverage,
                    genres = getGenres(data.genres),
                    posterPath = data.posterPath
                )

                if (codeNull == 1)
                    localDataSource.insertTvShow(tvShow)
                else if (codeNull == 2)
                    localDataSource.setDetailTvShow(tvShow)
            }
        }.asLiveData()
    }

    override fun getAllFavoriteMovies(): LiveData<PagedList<FavoriteMovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getAllFavoriteTvShow(): LiveData<PagedList<FavoriteTvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShow(), config).build()
    }

    override fun existFavoriteMovie(title: String?): Boolean {
        return localDataSource.existFavoriteMovie(title)
    }

    override fun insertFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity) {
        appExecutors.diskIO().execute { localDataSource.insertFavoriteMovie(favoriteMovieEntity) }
    }

    override fun insertFavoriteTvShow(favoriteTvShowEntity: FavoriteTvShowEntity) {
        appExecutors.diskIO().execute { localDataSource.insertFavoriteTvShow(favoriteTvShowEntity) }
    }

    override fun deleteFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity) {
        appExecutors.diskIO().execute { localDataSource.deleteFavoriteMovie(favoriteMovieEntity) }
    }

    override fun deleteFavoriteTvShow(favoriteTvShowEntity: FavoriteTvShowEntity) {
        appExecutors.diskIO().execute { localDataSource.deleteFavoriteTvShow(favoriteTvShowEntity) }
    }

}
