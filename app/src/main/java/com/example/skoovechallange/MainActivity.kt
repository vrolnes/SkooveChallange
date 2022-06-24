@file:OptIn(ExperimentalFoundationApi::class)

package com.example.skoovechallange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
                    MainScreen()
                }
            }
        }
    }

    @Composable
    fun MainScreen() {
        val mainViewModel: MainViewModel by viewModels()
        mainViewModel.prepareAudio(Constants.aOneWAV,Constants.bOneWAV)
        Column(modifier = Modifier.fillMaxSize()) { //Main Layout
            ImagesLayout(
                leftImage = Constants.aOneJPG,
                rightImage = Constants.bOneJPG
            )
            FastForwardButtons()
            PlayerButtons(onPlayButtonClick = { mainViewModel.playOrPauseAudio() }, onPlayButtonLongClick = { mainViewModel.resetAudio() })
        }
    }

    @Composable
    fun ImagesLayout(leftImage: String, rightImage: String) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box {
                ImageView(
                    imageUrl = leftImage
                )
            }
            Box {
                ImageView(
                    imageUrl = rightImage
                )
            }
        }
    }

    @Composable
    fun FastForwardButtons() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray),
            horizontalArrangement = Arrangement.SpaceAround
        ) { //Fast forward buttons layout
            Image(
                painter = painterResource(id = android.R.drawable.ic_media_ff),
                contentDescription = ""
            )
            Image(
                painter = painterResource(id = android.R.drawable.ic_media_ff),
                contentDescription = ""
            )

        }
    }

    @Composable
    fun PlayerButtons(onPlayButtonClick: () -> Unit, onPlayButtonLongClick: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red),
            horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) { // Player buttons layout
            Image(
                painter = painterResource(id = android.R.drawable.stat_notify_sync_noanim),
                contentDescription = ""
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_slideshow),
                    contentDescription = "",
                    Modifier.combinedClickable(onClick = onPlayButtonClick, onLongClick = onPlayButtonLongClick)
                )
                Text(text = "2:3:1")
            }
            Image(
                painter = painterResource(id = android.R.drawable.presence_online),
                contentDescription = ""
            )
            Image(
                painter = painterResource(id = android.R.drawable.presence_invisible),
                contentDescription = ""
            )
        }
    }
}