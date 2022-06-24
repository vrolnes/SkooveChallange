package com.example.skoovechallange.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

@Composable
fun ImageView(imageUrl: String) {
    SubcomposeAsyncImage(model = imageUrl, contentDescription = "", modifier = Modifier.width(360.dp), contentScale = ContentScale.Fit){
            SubcomposeAsyncImageContent()
    }
}