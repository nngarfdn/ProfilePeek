package com.apps.profilepeek.core.di

import android.content.Context
import androidx.room.Room
import com.apps.profilepeek.core.data.PersonRepositoryImpl
import com.apps.profilepeek.core.data.local.LocalDataSource
import com.apps.profilepeek.core.data.local.room.AppDatabase
import com.apps.profilepeek.core.data.remote.RemoteDataSource
import com.apps.profilepeek.core.data.remote.network.ApiService
import com.apps.profilepeek.core.data.remote.utils.Constants
import com.apps.profilepeek.core.domain.repository.PersonRepository
import com.apps.profilepeek.core.domain.usecase.GetPersonUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            AppDatabase.NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideOmdbApi(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(appDatabase: AppDatabase): LocalDataSource {
        return LocalDataSource(appDatabase.personDao())
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSource(apiService)
    }

    @Provides
    @Singleton
    fun providePersonRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): PersonRepository {
        return PersonRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Provides
    @Singleton
    fun provideGetPersonUseCase(personRepository: PersonRepository): GetPersonUseCase {
        return GetPersonUseCase(personRepository)
    }
}