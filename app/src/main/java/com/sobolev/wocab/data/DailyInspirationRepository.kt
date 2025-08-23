package com.sobolev.wocab.data

object DailyInspirationRepository {
    fun getDailyInspiration(): DailyInspirationModel {
        return DailyInspirationModel(
            movie = MovieOfTheDay(
                title = "The Shawshank Redemption",
                description = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                year = 1994
            ),
            book = BookOfTheDay(
                title = "1984",
                author = "George Orwell",
                description = "A dystopian novel about totalitarianism and surveillance society, following the life of Winston Smith in a world where Big Brother is always watching."
            ),
            quote = QuoteOfTheDay(
                text = "The only way to do great work is to love what you do.",
                author = "Steve Jobs"
            ),
            word = WordOfTheDay(
                word = "Serendipity",
                translation = "Счастливая случайность",
                phonetic = "/ˌserənˈdɪpəti/",
                examples = listOf(
                    "Meeting my future business partner at that coffee shop was pure serendipity.",
                    "Встреча с моим будущим бизнес-партнером в том кафе была чистой случайностью."
                ),
                otherMeanings = listOf(
                    "Счастливое совпадение",
                    "Неожиданная удача",
                    "Случайное открытие"
                )
            )
        )
    }
}
