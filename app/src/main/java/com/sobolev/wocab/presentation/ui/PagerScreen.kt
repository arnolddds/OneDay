package com.sobolev.wocab.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.sobolev.wocab.domain.model.DailyContent
import com.sobolev.wocab.presentation.ui.pages.BookPage
import com.sobolev.wocab.presentation.ui.pages.MeaningsPage
import com.sobolev.wocab.presentation.ui.pages.MoviePage
import com.sobolev.wocab.presentation.ui.pages.QuotePage
import com.sobolev.wocab.presentation.ui.pages.WordPage

@Composable
fun PagerScreen(
    content: DailyContent
) {
    val pagerState = rememberPagerState(pageCount = { 5 })
    var shouldScrollToTop by remember { mutableStateOf(false) }

    LaunchedEffect(shouldScrollToTop) {
        if (shouldScrollToTop) {
            runCatching { pagerState.animateScrollToPage(0) }
                .onFailure { pagerState.scrollToPage(0) }
            shouldScrollToTop = false
        }
    }

    VerticalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        when (page) {
            0 -> MoviePage(content.movie)
            1 -> BookPage(content.book)
            2 -> QuotePage(content.quote)
            3 -> WordPage(content.word)
            4 -> MeaningsPage(
                onGoTop = { shouldScrollToTop = true }
            )
        }
    }
}



