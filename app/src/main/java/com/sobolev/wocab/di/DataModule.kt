package com.sobolev.wocab.di

import com.sobolev.wocab.data.datasource.local.DailyLocalDataSource
import com.sobolev.wocab.data.datasource.local.MockDailyLocalDataSource
import com.sobolev.wocab.data.repository.DailyRepositoryImpl
import com.sobolev.wocab.domain.repository.DailyRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindModule {
    @Binds
    abstract fun bindDailyRepository(impl: DailyRepositoryImpl): DailyRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DataProvideModule {
    @Provides
    @Singleton
    fun provideDailyLocalDataSource(): DailyLocalDataSource = MockDailyLocalDataSource()
}


