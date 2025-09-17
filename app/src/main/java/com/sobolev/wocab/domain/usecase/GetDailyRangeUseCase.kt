package com.sobolev.wocab.domain.usecase

import com.sobolev.wocab.domain.model.DailyContent
import com.sobolev.wocab.domain.repository.DailyRepository
import java.time.LocalDate

class GetDailyRangeUseCase(
    private val repository: DailyRepository
) {
    suspend operator fun invoke(start: LocalDate, end: LocalDate): List<DailyContent> =
        repository.getDailyRange(start, end)
}



