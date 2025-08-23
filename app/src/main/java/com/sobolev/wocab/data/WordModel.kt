package com.sobolev.wocab.data

data class WordModel(
    val word: String,
    val translation: String,
    val phonetic: String,
    val examples: List<String>,
    val otherMeanings: List<String>,
    val audioFileName: String
) 