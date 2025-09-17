package com.sobolev.wocab.di

import com.sobolev.wocab.data.remote.DailyApi
import com.sobolev.wocab.data.repository.DailyRepositoryImpl
import com.sobolev.wocab.domain.repository.DailyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindModule {
    @Binds
    abstract fun bindDailyRepository(impl: DailyRepositoryImpl): DailyRepository
}


