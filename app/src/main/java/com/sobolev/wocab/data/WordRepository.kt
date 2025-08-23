package com.sobolev.wocab.data

object WordRepository {
    fun getWordOfTheDay(): WordModel {
        return WordModel(
            word = "Serendipity",
            translation = "Счастливая случайность",
            phonetic = "/ˌserənˈdɪpəti/",
            examples = listOf(
                "Finding that perfect coffee shop was pure serendipity.",
                "Найти то идеальное кафе было чистой случайностью."
            ),
            otherMeanings = listOf(
                "Неожиданная удача",
                "Счастливое стечение обстоятельств",
                "Приятная неожиданность"
            )
        )
    }
} 