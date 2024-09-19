package com.example.multimodule.presention.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.ImageResult
import kotlin.math.max
import kotlin.math.truncate

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun Gallery(modifier: Modifier=Modifier,
            images:List<String> ,
            imagesSize:Dp=40.dp,
            spaceBetween:Dp=10.dp,
            imagesShape:CornerBasedShape=Shapes().small
            ){
    BoxWithConstraints(modifier=Modifier) {
        val numberOfVisibleImage = remember {
            derivedStateOf {
                max(
                    a=0,
                    b = this.maxWidth.div(spaceBetween + imagesSize).toInt().minus(1)
                )
            }

        }

        val remainingImages= remember {
            derivedStateOf {
                images.size-numberOfVisibleImage.value
            }
        }

        Row {
            images.take(numberOfVisibleImage.value).forEach { image->
                AsyncImage(
                    modifier = Modifier.clip(imagesShape).size(imagesSize),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),
                    contentDescription ="Gallery images"
                )
                Spacer(modifier=Modifier.width(spaceBetween))
            }

            if(remainingImages.value<0)
            {
                LastImagesOverlay(
                    imageSize =imagesSize ,
                    remainingImages = remainingImages.value,
                    imageShape=imagesShape)
            }
        }
    }

}