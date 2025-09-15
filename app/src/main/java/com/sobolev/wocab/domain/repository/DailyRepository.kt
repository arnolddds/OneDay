package com.sobolev.wocab.domain.repository

import com.sobolev.wocab.domain.model.DailyContent


interface DailyRepository {
    suspend fun getDailyContent(): DailyContent
}


