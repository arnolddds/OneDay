package com.sobolev.wocab.data.mapper

import com.sobolev.wocab.data.dto.*
import com.sobolev.wocab.domain.model.*

fun DailyDto.toDomain(): DailyContent = DailyContent(
    movie = movie.toDomain(),
    book = book.toDomain(),
    quote = quote.toDomain(),
    word = word.toDomain()
)

private fun MovieDto.toDomain(): Movie = Movie(
    title = title,
    description = description,
    year = year
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
    word = word,
    translation = translation,
    phonetic = phonetic,
    otherMeanings = otherMeanings
)


