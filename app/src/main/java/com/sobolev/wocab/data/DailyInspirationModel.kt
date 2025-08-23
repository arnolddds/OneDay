package com.sobolev.wocab.data

data class DailyInspirationModel(
    val movie: MovieOfTheDay,
    val book: BookOfTheDay,
    val quote: QuoteOfTheDay,
    val word: WordOfTheDay
)

data class MovieOfTheDay(
    val title: String,
    val description: String,
    val year: Int
)

data class BookOfTheDay(
    val title: String,
    val author: String,
    val description: String
)

data class QuoteOfTheDay(
    val text: String,
    val author: String
)

data class WordOfTheDay(
    val word: String,
    val translation: String,
    val phonetic: String,
    val examples: List<String>,
    val otherMeanings: List<String>
) 