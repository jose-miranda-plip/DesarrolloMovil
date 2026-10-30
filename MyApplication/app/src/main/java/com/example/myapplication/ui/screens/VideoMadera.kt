package com.example.myapplication.ui.screens

import android.net.Uri
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.myapplication.R

@Composable
fun VideoMadera() {
    val context = LocalContext.current

    val videoView = remember {
        VideoView(context).apply {
            val uri = Uri.parse(
                "android.resource://${context.packageName}/${R.raw.video_madera}"
            )

            setVideoURI(uri)

            val controles = MediaController(context)
            controles.setAnchorView(this)
            setMediaController(controles)
        }
    }

    AndroidView(
        factory = { videoView },
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    )

    DisposableEffect(videoView) {
        onDispose {
            videoView.stopPlayback()
        }
    }
}