package com.sobolev.wocab.data.repository

import com.sobolev.wocab.data.datasource.local.DailyLocalDataSource
import com.sobolev.wocab.data.mapper.toDomain
import com.sobolev.wocab.domain.model.DailyContent
import com.sobolev.wocab.domain.repository.DailyRepository
import javax.inject.Inject

class DailyRepositoryImpl @Inject constructor(
    private val localDataSource: DailyLocalDataSource
) : DailyRepository {
    override suspend fun getDailyContent(): DailyContent {
        return localDataSource.getDaily().toDomain()
    }
}


