package com.sobolev.wocab.data.dto

data class DailyDto(
    val movie: MovieDto,
    val book: BookDto,
    val quote: QuoteDto,
    val word: WordDto
)

data class MovieDto(
    val title: String,
    val description: String,
    val year: Int
)

data class BookDto(
    val title: String,
    val author: String,
    val description: String
)

data class QuoteDto(
    val text: String,
    val author: String
)

data class WordDto(
    val word: String,
    val translation: String,
    val phonetic: String,
    val otherMeanings: List<String>
)


