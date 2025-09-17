package com.sobolev.wocab.domain.repository

import com.sobolev.wocab.domain.model.DailyContent
import java.time.LocalDate


interface DailyRepository {
    suspend fun getDaily(date: LocalDate? = null): DailyContent
    suspend fun getDailyRange(start: LocalDate, end: LocalDate): List<DailyContent>
}


