package com.sobolev.wocab.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.sobolev.wocab.data.dto.*
import com.sobolev.wocab.domain.model.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun DailyDto.toDomain(): DailyContent = DailyContent(
    movie = movie.toDomain(),
    book = book.toDomain(),
    quote = quote.toDomain(),
    word = word.toDomain(),
    date = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE)
)

private fun MovieDto.toDomain(): Movie = Movie(
    title = title,
    description = description,
    rating = rating,
    year = year,
    imageUrl = imageUrl
)

private fun BookDto.toDomain(): Book = Book(
    title = title,
    author = author,
    description = description
)

private fun QuoteDto.toDomain(): Quote = Quote(
    text = text,
    author = author
)

private fun WordDto.toDomain(): Word = Word(
    word = english,
    translation = translation,
    phonetic = transcription
)


