package com.example.skoovechallange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.skoovechallange.data.Constants
import com.example.skoovechallange.ui.components.ImageView
import com.example.skoovechallange.ui.theme.SkooveChallangeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkooveChallangeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ImageView(
                        imageUrl = Constants.aOneJPG
                    )
                }
            }
        }
    }
}