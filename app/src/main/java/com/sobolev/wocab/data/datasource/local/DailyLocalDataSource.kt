package com.sobolev.wocab.data.datasource.local

import com.sobolev.wocab.data.dto.*

interface DailyLocalDataSource {
    suspend fun getDaily(): DailyDto
}

class MockDailyLocalDataSource : DailyLocalDataSource {
    override suspend fun getDaily(): DailyDto {
        return DailyDto(
            movie = MovieDto(
                title = "The Shawshank Redemption",
                description = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                year = 1994
            ),
            book = BookDto(
                title = "1984",
                author = "George Orwell",
                description = "A dystopian novel about totalitarianism and surveillance society, following the life of Winston Smith in a world where Big Brother is always watching."
            ),
            quote = QuoteDto(
                text = "The only way to do great work is to love what you do.",
                author = "Steve Jobs"
            ),
            word = WordDto(
                word = "Serendipity",
                translation = "Счастливая случайность",
                phonetic = "/ˌserənˈdɪpəti/",
                otherMeanings = listOf(
                    "Счастливое совпадение",
                    "Неожиданная удача",
                    "Случайное открытие"
                )
            )
        )
    }
}


