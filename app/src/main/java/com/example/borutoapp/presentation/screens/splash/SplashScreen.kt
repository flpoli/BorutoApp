package com.example.borutoapp.presentation.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.borutoapp.R
import com.example.borutoapp.ui.theme.Purple500
import com.example.borutoapp.ui.theme.Purple700
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SplashScreen(navController: NavHostController){
    Splash()
}

@Composable
fun Splash(){
    Box(
        modifier = Modifier
            .background(Brush.verticalGradient(listOf(Purple700, Purple500)))
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "logo"
            )
    }
}

@Composable
@Preview
fun SplashScreenPreview(){
    Splash()
}