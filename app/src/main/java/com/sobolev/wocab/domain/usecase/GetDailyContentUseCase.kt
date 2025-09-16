package com.sobolev.wocab.domain.usecase

import com.sobolev.wocab.domain.model.DailyContent
import com.sobolev.wocab.domain.repository.DailyRepository

class GetDailyContentUseCase(
    private val repository: DailyRepository
) {
    suspend operator fun invoke(): DailyContent = repository.getDailyContent()
}


