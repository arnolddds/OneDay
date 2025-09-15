package com.sobolev.wocab.domain.model

data class Word(
    val word: String,
    val translation: String,
    val phonetic: String,
    val otherMeanings: List<String>
)


