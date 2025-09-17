package com.sobolev.wocab.di

import com.sobolev.wocab.domain.repository.DailyRepository
import com.sobolev.wocab.domain.usecase.GetDailyContentUseCase
import com.sobolev.wocab.domain.usecase.GetDailyRangeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideGetDailyContentUseCase(repository: DailyRepository): GetDailyContentUseCase =
        GetDailyContentUseCase(repository)

    @Provides
    @Singleton
    fun provideGetDailyRangeUseCase(repository: DailyRepository): GetDailyRangeUseCase =
        GetDailyRangeUseCase(repository)
}


