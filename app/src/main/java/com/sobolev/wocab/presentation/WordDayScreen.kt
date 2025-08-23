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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement


import kotlinx.coroutines.launch
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import com.sobolev.wocab.data.WordModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WordDayScreen(
    word: WordModel
) {
    val pagerState = rememberPagerState()
    
    VerticalPager(
        count = 5,
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        when (page) {
            0 -> WordTranslationPage(word)
            1 -> ExamplePage(word)
            2 -> ExamplePage(word)
            3 -> PronunciationPage(word)
            4 -> OtherMeaningsPage(word.otherMeanings, pagerState)
        }
    }
}

@Composable
private fun WordTranslationPage(word: WordModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        // Main content centered
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
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
        
        // Simple text hint at the bottom
        Text(
            text = "Swipe to see more",
            color = LocalContentColor.current.copy(alpha = 0.45f),
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 28.dp)
        )
    }
}

@Composable
private fun ExamplePage(word: WordModel) {
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
            // Russian translation first
            Text(
                text = buildAnnotatedString {
                    val russianExample = word.examples[1]
                    val parts = russianExample.split(word.translation)
                    if (parts.size > 1) {
                        append(parts[0])
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(word.translation)
                        }
                        append(parts[1])
                    } else {
                        append(russianExample)
                    }
                },
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                lineHeight = 28.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // English sentence second
            Text(
                text = buildAnnotatedString {
                    val englishExample = word.examples[0]
                    val parts = englishExample.split(word.word)
                    if (parts.size > 1) {
                        append(parts[0])
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(word.word)
                        }
                        append(parts[1])
                    } else {
                        append(englishExample)
                    }
                },
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                lineHeight = 28.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
        
        // Swipe hint at the bottom
        Text(
            text = "Swipe to see more",
            color = LocalContentColor.current.copy(alpha = 0.45f),
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 28.dp)
        )
    }
}

@Composable
private fun PronunciationPage(
    word: WordModel
) {
    val context = LocalContext.current
    val textToSpeechPlayer = remember { TextToSpeechPlayer(context) }
    
    DisposableEffect(Unit) {
        onDispose {
            textToSpeechPlayer.release()
        }
    }
    
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
                onClick = {
                    // Speak the target word using TextToSpeech
                    textToSpeechPlayer.speak(word.word)
                },
                modifier = Modifier.size(80.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play pronunciation",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
        
        // Swipe hint at the bottom
        Text(
            text = "Swipe to see more",
            color = LocalContentColor.current.copy(alpha = 0.45f),
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 28.dp)
        )
    }
}

@Composable
private fun OtherMeaningsPage(
    meanings: List<String>,
    pagerState: com.google.accompanist.pager.PagerState
) {
    val coroutineScope = rememberCoroutineScope()
    
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
        
        // Go to top button with proper error handling
        Button(
            onClick = {
                coroutineScope.launch {
                    try {
                        pagerState.animateScrollToPage(0)
                    } catch (e: Exception) {
                        // Fallback to instant scroll if animation fails
                        pagerState.scrollToPage(0)
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        ) {
            Text("Go to the top")
        }
    }
}