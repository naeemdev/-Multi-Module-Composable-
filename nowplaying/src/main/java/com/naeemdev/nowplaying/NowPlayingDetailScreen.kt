package com.naeemdev.nowplaying

import androidx.compose.runtime.Composable
import com.naeemdev.common.theme.MultiModuleComposable

@Composable
fun NowPlayingDetailScreen() {
    MultiModuleComposable(darkTheme = true) {
        NowPlayingDetailContent()
    }
}

@Composable
fun NowPlayingDetailContent() {
    /* do something */
}