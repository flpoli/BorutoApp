package com.example.borutoapp.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.R
import com.example.borutoapp.presentation.components.InfoBox
import com.example.borutoapp.presentation.components.OrderedList
import com.example.borutoapp.ui.LARGE_PADDING
import com.example.borutoapp.ui.MEDIUM_PADDING
import com.example.borutoapp.ui.SMALL_PADDING
import com.example.borutoapp.ui.theme.titleColor
import com.example.borutoapp.util.Constants.BASE_URL

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navController: NavHostController,
    selectedHero: Hero?
) {


    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 140.dp,
        sheetContent = {
            if (selectedHero != null) {
                BottomSheetContent(selectedHero = selectedHero)
            }
        },
        content = {

            selectedHero?.let{
                hero ->
                    BackgroundContent(
                        heroImage = hero.image,
                        onCloseClicked = {
                            navController.popBackStack()
                        }
                    )
            }

                

        }
    )
}

@Composable
fun BottomSheetContent(
    selectedHero: Hero,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor

){

    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = LARGE_PADDING)
    ){

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .weight(2f),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "icon details screen",
                tint = contentColor
            )
            Text(
                modifier = Modifier
                    .weight(8f),
                text = selectedHero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold,

            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            InfoBox(
                icon = painterResource(id = R.drawable.ic_bolt),
                iconColor = infoBoxIconColor,
                bigText = "${selectedHero.power}",
                smallText = "Power",
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.ic_calendar),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.month,
                smallText = "Month",
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.ic_cake),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.day,
                smallText = "Birthday",
                textColor = contentColor
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "About",
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(MEDIUM_PADDING),
            text = selectedHero.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = 7
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            OrderedList(
                title ="Family",
                items = selectedHero.family,
                textColor = contentColor
            )
            OrderedList(
                title = "Abilities",
                items = selectedHero.abilities,
                textColor = contentColor
            )
            OrderedList(
                title = "Nature Types",
                items = selectedHero.natureTypes,
                textColor = contentColor
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
fun BackgroundContent(

    heroImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit

){

    val imageUrl  = "$BASE_URL${heroImage}"
    val painter = rememberImagePainter(imageUrl) { error(R.drawable.ic_placeholder) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(imageFraction)
                .align(Alignment.TopStart),
            painter = painter,
            contentDescription = "background image",
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ){
            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = { onCloseClicked() }
            ){
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.Close,
                    contentDescription = "close button",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
@Preview
fun BottomSheetContentPreview(){

    BottomSheetContent(selectedHero = Hero(
        id = 1,
        name = "naruto",
        image = "",
        about = stringResource(R.string.preview_text),
        rating = 4.5,
        power = 0,
        month = "Oct",
        day = "1st",
        family = listOf("Minato", "Kushina", "Boruto"),
        abilities = listOf("Sage mode", "Shadow Clone"),
        natureTypes = listOf("Earth", "Wind")
    ))
}