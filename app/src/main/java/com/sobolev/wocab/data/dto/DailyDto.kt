package com.sobolev.wocab.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyDto(
    val movie: MovieDto,
    val book: BookDto,
    val quote: QuoteDto,
    val word: WordDto,
    val date: String
)

@Serializable
data class MovieDto(
    val id: Int,
    val title: String,
    val description: String,
    val rating: Double,
    val year: Int,
    val imageUrl: String,
    val date: String
)

@Serializable
data class BookDto(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val date: String
)

@Serializable
data class QuoteDto(
    val id: Int,
    val text: String,
    val author: String,
    val date: String
)

@Serializable
data class WordDto(
    val id: Int,
    @SerialName("english") val english: String,
    val translation: String,
    @SerialName("transcription") val transcription: String,
    val date: String
)


