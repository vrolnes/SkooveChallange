@file:OptIn(ExperimentalFoundationApi::class)

package com.example.skoovechallange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
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
import com.example.skoovechallange.ui.components.ImageView
import com.example.skoovechallange.ui.theme.SkooveChallangeTheme
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel: MainViewModel by viewModels()
        mainViewModel.prepareAudio()
        setContent {
            SkooveChallangeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val mtiState = mainViewModel.mtiState
                    val lefFlashState = mainViewModel.leftFlashState
                    val rightFlashState = mainViewModel.rightFlashState
                    val lefImage = mainViewModel.leftImage
                    val rightImage = mainViewModel.rightImage
                    MainScreen(mainViewModel, mtiState, lefFlashState, rightFlashState, lefImage, rightImage)
                }
            }
            BackHandler(enabled = true) {
                // If yes, run the fancy new function to end the app and
                //  remove it from the task list.
                android.os.Process.killProcess(android.os.Process.myPid())
                exitProcess(1)
            }
        }
    }

    @Composable
    fun MainScreen(
        mainViewModel: MainViewModel,
        MTI: String,
        leftFlashState: Boolean,
        rightFlashState: Boolean,
        lefImage: String,
        rightImage: String
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) { //Main Layout
            ImagesLayout(
                leftImage = lefImage,
                rightImage = rightImage
            )
            FastForwardButtons(mainViewModel)
            PlayerButtons(
                onPlayButtonClick = { mainViewModel.playOrPauseAudio() },
                onPlayButtonLongClick = { mainViewModel.resetAudio() },
                MTI,
                leftFlashState,
                rightFlashState
            )
        }
    }

    @Composable
    fun ImagesLayout(leftImage: String, rightImage: String) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
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
    fun FastForwardButtons(mainViewModel: MainViewModel) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) { //Fast forward buttons layout
            Image(
                painter = painterResource(id = android.R.drawable.ic_media_ff),
                contentDescription = "",
                modifier = Modifier.clickable {
                    mainViewModel.prepareLeftPlayerTrack()
                }
            )
            Image(
                painter = painterResource(id = android.R.drawable.ic_media_ff),
                contentDescription = "",
                modifier = Modifier.clickable {
                    mainViewModel.prepareRightPlayerTrack()
                }
            )

        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun PlayerButtons(
        onPlayButtonClick: () -> Unit,
        onPlayButtonLongClick: () -> Unit,
        MTI: String,
        leftFlash: Boolean,
        rightFlash: Boolean
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
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
                    Modifier.combinedClickable(
                        onClick = onPlayButtonClick,
                        onLongClick = onPlayButtonLongClick
                    )
                )
                Text(text = MTI)
            }
            Image(
                painter = painterResource(id = if (leftFlash) android.R.drawable.presence_online else android.R.drawable.presence_invisible),
                contentDescription = ""
            )
            Image(
                painter = painterResource(id = if (rightFlash) android.R.drawable.presence_online else android.R.drawable.presence_invisible),
                contentDescription = ""
            )
        }
    }
}