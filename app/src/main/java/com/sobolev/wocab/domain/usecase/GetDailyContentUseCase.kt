package com.sobolev.wocab.domain.usecase

import com.sobolev.wocab.domain.model.DailyContent
import com.sobolev.wocab.domain.repository.DailyRepository
import java.time.LocalDate

class GetDailyContentUseCase(
    private val repository: DailyRepository
) {
    suspend operator fun invoke(date: LocalDate? = null): DailyContent = repository.getDaily(date)
}


