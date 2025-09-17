package com.sobolev.wocab.domain.model

import java.time.LocalDate

data class DailyContent(
    val movie: Movie,
    val book: Book,
    val quote: Quote,
    val word: Word,
    val date: LocalDate
)