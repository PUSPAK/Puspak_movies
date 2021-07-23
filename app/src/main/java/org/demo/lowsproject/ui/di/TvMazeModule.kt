package org.demo.lowsproject.ui.di

import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.demo.lowsproject.BuildConfig
import org.demo.lowsproject.BuildConfig.API_END_POINT
import org.demo.lowsproject.data.ResponseHandler
import org.demo.lowsproject.data.TvMazeApi
import org.demo.lowsproject.data.database.AppDatabase
import org.demo.lowsproject.data.datasource.*
import org.demo.lowsproject.data.mapper.*
import org.demo.lowsproject.data.repository.EpisodeRepository
import org.demo.lowsproject.data.repository.EpisodeRepositoryImpl
import org.demo.lowsproject.data.repository.TvShowRepository
import org.demo.lowsproject.data.repository.TvShowRepositoryImpl
import org.demo.lowsproject.data.utils.DefaultDispatcherProvider
import org.demo.lowsproject.data.utils.DispatcherProvider
import org.demo.lowsproject.domain.usecase.*
import org.demo.lowsproject.domain.usecase.interfaces.*
import org.demo.lowsproject.ui.detail.TvShowDetailViewModel
import org.demo.lowsproject.ui.detail.TvShowDetailViewState
import org.demo.lowsproject.ui.favorite.FavoriteViewModel
import org.demo.lowsproject.ui.favorite.FavoriteViewState
import org.demo.lowsproject.ui.home.HomeViewModel
import org.demo.lowsproject.ui.home.HomeViewState
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val KOIN_RETROFIT = "KOIN_RETROFIT"
const val KOIN_ROOM = "KOIN_ROOM"

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val tvMazeModule = module {
    viewModel {
        HomeViewModel(
                get<GetTvShowByPageUseCase>(),
                get<GetTvShowByTextUseCase>(),
                get<HomeViewState>()
        )
    }

    viewModel {
        TvShowDetailViewModel(
            get<GetTvShowByIdUseCase>(),
            get<GetEpisodeByShowUseCase>(),
            get<SaveFavoriteTvShowUseCase>(),
            get<GetFavoriteTvShowByIdUseCase>(),
            get<RemoveFavoriteTvShowByIdUseCase>(),
            get<TvShowDetailViewState>()
        )
    }

    viewModel {
        FavoriteViewModel(
            get<GetFavoriteListUseCase>(),
            get<FavoriteViewState>()
        )
    }

    factory {
        GetEpisodeByShow(
            get<EpisodeRepository>(),
            get<DispatcherProvider>()
        ) as GetEpisodeByShowUseCase
    }

    factory {
        RemoveFavoriteTvShowById(
            get<TvShowRepository>(),
            get<DispatcherProvider>()
        ) as RemoveFavoriteTvShowByIdUseCase
    }

    factory {
        GetFavoriteTvShowById(
            get<TvShowRepository>(),
            get<DispatcherProvider>()
        ) as GetFavoriteTvShowByIdUseCase
    }

    factory {
        GetFavoriteList(
            get<TvShowRepository>(),
            get<DispatcherProvider>()
        ) as GetFavoriteListUseCase
    }

    factory {
        SaveFavoriteTvShow(
            get<TvShowRepository>(),
            get<DispatcherProvider>()
        ) as SaveFavoriteTvShowUseCase
    }

    factory {
        GetTvShowById(get<TvShowRepository>()) as GetTvShowByIdUseCase
    }

    factory {
        GetTvShowByPage(get<TvShowRepository>()) as GetTvShowByPageUseCase
    }

    factory {
        GetTvShowByText(get<TvShowRepository>()) as GetTvShowByTextUseCase
    }

    factory {
        DefaultDispatcherProvider() as DispatcherProvider
    }

    factory {
        EpisodeRepositoryImpl(get<EpisodeRemoteDataSource>()) as EpisodeRepository
    }

    factory {
        TvShowRepositoryImpl(
            get<TvShowRemoteDataSource>(),
            get<TvShowLocalDataSource>()
        ) as TvShowRepository
    }

    factory {
        TvShowRemoteDataSourceImpl(
            get<TvMazeApi>(),
            get<ResponseHandler>(),
            get<TvShowDtoToTvShowModelMapper>(),
            get<TvShowSearchDtoToTvShowModelMapper>(),
            get<TvShowDetailDtoToTvShowDetailModelMapper>()
        ) as TvShowRemoteDataSource
    }

    factory {
        TvShowLocalDataSourceImpl(
            get<AppDatabase>(),
            get<TvShowDboToTvShowModelMapper>()
        ) as TvShowLocalDataSource
    }

    factory {
        EpisodeRemoteDataSourceImpl(
            get<TvMazeApi>(),
            get<ResponseHandler>(),
            get<EpisodeDtoToEpisodeModelMapper>()
        ) as EpisodeRemoteDataSource
    }

    factory {
        ResponseHandler()
    }

    factory {
        TvShowDtoToTvShowModelMapper()
    }

    factory {
        TvShowSearchDtoToTvShowModelMapper()
    }

    factory {
        TvShowDetailDtoToTvShowDetailModelMapper()
    }

    factory {
        EpisodeDtoToEpisodeModelMapper()
    }

    factory {
        TvShowDboToTvShowModelMapper()
    }

    factory {
        HomeViewState()
    }

    factory {
        TvShowDetailViewState()
    }

    factory {
        FavoriteViewState()
    }

    factory {
        get<Retrofit>(named(KOIN_RETROFIT)).create(TvMazeApi::class.java)
    }

    factory {
        get<AppDatabase>(named(KOIN_ROOM))
    }

    single(named(KOIN_RETROFIT)) {
        Retrofit.Builder()
            .baseUrl(API_END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .build()
    }

    single(named(KOIN_ROOM)) {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "database"
        ).build()
    }
}