package com.sobolev.wocab.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.sobolev.wocab.data.WordModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WordDayScreen(
    word: WordModel,
    isPlaying: Boolean,
    onPlayAudio: () -> Unit,
    onPlayingStateChange: (Boolean) -> Unit
) {
    val pagerState = rememberPagerState()
    val context = LocalContext.current
    val audioPlayer = remember { AudioPlayer(context) }
    
    DisposableEffect(Unit) {
        onDispose {
            audioPlayer.release()
        }
    }
    
    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            audioPlayer.playAudio(word.audioFileName) {
                onPlayingStateChange(false)
            }
        }
    }
    
    HorizontalPager(
        count = 5,
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        when (page) {
            0 -> WordTranslationPage(word)
            1 -> ExamplePage(word.examples[0], 1)
            2 -> ExamplePage(word.examples[1], 2)
            3 -> PronunciationPage(word, isPlaying, onPlayAudio)
            4 -> OtherMeaningsPage(word.otherMeanings, pagerState)
        }
    }
}

@Composable
private fun WordTranslationPage(word: WordModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = word.word,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = word.translation,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun ExamplePage(example: String, pageNumber: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Example $pageNumber",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.secondary
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Text(
                text = example,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                lineHeight = 28.sp
            )
        }
    }
}

@Composable
private fun PronunciationPage(
    word: WordModel,
    isPlaying: Boolean,
    onPlayAudio: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Pronunciation",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.secondary
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Text(
                text = word.phonetic,
                fontSize = 32.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(48.dp))
            
            FloatingActionButton(
                onClick = onPlayAudio,
                modifier = Modifier.size(80.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play pronunciation",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

@Composable
private fun OtherMeaningsPage(
    meanings: List<String>,
    pagerState: com.google.accompanist.pager.PagerState
) {
    var shouldScrollToTop by remember { mutableStateOf(false) }
    
    // Handle the scroll animation in proper Compose context
    LaunchedEffect(shouldScrollToTop) {
        if (shouldScrollToTop) {
            pagerState.animateScrollToPage(0)
            shouldScrollToTop = false
        }
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Other Meanings",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            meanings.forEachIndexed { index, meaning ->
                Text(
                    text = "${index + 1}. $meaning",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
        
        Button(
            onClick = { shouldScrollToTop = true },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        ) {
            Text("Go to the top")
        }
    }
} 