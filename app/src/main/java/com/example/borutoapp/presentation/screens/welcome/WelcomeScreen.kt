package com.example.borutoapp.presentation.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.borutoapp.domain.model.OnBoardingPage
import com.example.borutoapp.ui.EXTRA_LARGE_PADDING
import com.example.borutoapp.ui.SMALL_PADDING
import com.example.borutoapp.ui.theme.descriptionColor
import com.example.borutoapp.ui.theme.title
import com.example.borutoapp.ui.theme.welcomeScreenBackgroundColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import java.nio.file.WatchEvent

@ExperimentalPagerApi
@Composable
fun WelcomeScreen(navController: NavHostController){

    val pages = listOf(OnBoardingPage.First, OnBoardingPage.Second, OnBoardingPage.Third)
    val pagerState = rememberPagerState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.welcomeScreenBackgroundColor)

    ){
        HorizontalPager(
            count = 3,
            state = pagerState,
            verticalAlignment = Alignment.Top
        
        ) {
           position -> PagerScreen(onBoardingPage = pages[position])
        }
    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage){


    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = "Onboard image"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = EXTRA_LARGE_PADDING),
            text = onBoardingPage.title,
            color = MaterialTheme.colors.title,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center

        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = SMALL_PADDING),
            text = onBoardingPage.description,
            color = MaterialTheme.colors.descriptionColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

    }
}

