package com.sobolev.wocab.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sobolev.wocab.data.DailyInspirationModel

@Composable
fun DailyInspirationScreen(
    inspiration: DailyInspirationModel
) {
    val pagerState = rememberPagerState(pageCount = { 5 })
    var shouldScrollToTop by remember { mutableStateOf(false) }
    
    // Handle scroll to top when triggered
    LaunchedEffect(shouldScrollToTop) {
        if (shouldScrollToTop) {
            try {
                pagerState.animateScrollToPage(0)
            } catch (e: Exception) {
                // Fallback to instant scroll if animation fails
                pagerState.scrollToPage(0)
            }
            shouldScrollToTop = false
        }
    }
    
    VerticalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        when (page) {
            0 -> MovieOfTheDayPage(inspiration.movie)
            1 -> BookOfTheDayPage(inspiration.book)
            2 -> QuoteOfTheDayPage(inspiration.quote)
            3 -> WordOfTheDayPage(inspiration.word)
            4 -> OtherMeaningsPage(
                meanings = inspiration.word.otherMeanings,
                onGoTop = { shouldScrollToTop = true }
            )
        }
    }
}

@Composable
private fun MovieOfTheDayPage(movie: com.sobolev.wocab.data.MovieOfTheDay) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "🎬",
                fontSize = 64.sp,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = movie.title,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Serif,
                color = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "(${movie.year})",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            
            Spacer(modifier = Modifier.height(18.dp))
            
            Text(
                text = movie.description,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                fontFamily = FontFamily.SansSerif,
                lineHeight = 24.sp
            )
        }
        
        Hint(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun BookOfTheDayPage(book: com.sobolev.wocab.data.BookOfTheDay) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "📚",
                fontSize = 64.sp,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = book.title,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Serif,
                color = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "by ${book.author}",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = book.description,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                fontFamily = FontFamily.SansSerif,
                lineHeight = 22.sp
            )
        }
        
        Hint(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun QuoteOfTheDayPage(quote: com.sobolev.wocab.data.QuoteOfTheDay) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "💬",
                fontSize = 64.sp,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "\"${quote.text}\"",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Serif,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
                lineHeight = 28.sp,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "— ${quote.author}",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )
        }
        
        Hint(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun WordOfTheDayPage(word: com.sobolev.wocab.data.WordOfTheDay) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "📝",
                fontSize = 64.sp,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = word.word,
                fontSize = 52.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Serif,
                color = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = word.translation,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                fontFamily = FontFamily.SansSerif
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = word.phonetic,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                fontFamily = FontFamily.Monospace
            )
        }
        
        Hint(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun OtherMeaningsPage(
    meanings: List<String>,
    onGoTop: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            meanings.forEach { meaning ->
                Text(
                    text = meaning,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
        }
        
        Button(
            onClick = onGoTop,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f)
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = "Go to the top",
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun Hint(
    modifier: Modifier = Modifier
) {
    Text(
        text = "Swipe to see more",
        color = LocalContentColor.current.copy(alpha = 0.45f),
        fontSize = 18.sp,
        modifier = modifier
            .padding(bottom = 36.dp)
    )
}