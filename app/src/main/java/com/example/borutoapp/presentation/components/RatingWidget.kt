package com.example.borutoapp.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.borutoapp.R
import com.example.borutoapp.ui.theme.LightGrey
import com.example.borutoapp.ui.theme.StarColor

@Composable
fun RatingWidget(
    modifier: Modifier,
    rating: Double
){

    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {

        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val starPathBounds = remember {

        starPath.getBounds()
    }

    FilledStar(starPath = starPath, starPathBounds = starPathBounds, scaleFactor = 3f)
}

@Composable
fun FilledStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float
){

    Canvas(modifier = Modifier.size(24.dp)){
        val canvasSize = this.size

        scale(scale = scaleFactor) {
            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height
            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)

            translate(left, top){
                drawPath(
                    path = starPath,
                    color = StarColor
                )
            }
        }
    }
}

@Composable
fun HalfFilledStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float){

    Canvas(modifier = Modifier.size(24.dp)){
        val canvasSize = this.size

        scale(scale = scaleFactor) {
            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height
            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)

            translate(left, top){
                drawPath(
                    path = starPath,
                    color = LightGrey.copy(alpha = 0.5f)
                )
                clipPath(path = starPath){

                    drawRect(
                        color = StarColor,
                        size = Size(
                            width = starPathBounds.maxDimension / 1.7f,
                            height = starPathBounds.maxDimension * scaleFactor
                        )
                    )

                }
            }
        }
    }
}

@Composable
fun EmptyStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float
){
    Canvas(modifier = Modifier.size(24.dp)){
        val canvasSize = this.size

        scale(scale = scaleFactor) {
            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height
            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)

            translate(left, top){
                drawPath(
                    path = starPath,
                    color = LightGrey.copy(alpha = 0.5f)
                )
            }
        }
    }
}


@Preview
@Composable
fun FilledStarPreview(){
    RatingWidget(modifier = Modifier, rating = 1.0)
}
@Preview
@Composable
fun HalfFilledStarPreview(){

    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {

        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val starPathBounds = remember {

        starPath.getBounds()
    }

    HalfFilledStar(starPath = starPath, starPathBounds = starPathBounds, scaleFactor = 3f)
}
@Preview
@Composable
fun EmptyStarPreview(){
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {

        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val starPathBounds = remember {

        starPath.getBounds()
    }

    EmptyStar(starPath = starPath, starPathBounds = starPathBounds, scaleFactor = 3f)
}