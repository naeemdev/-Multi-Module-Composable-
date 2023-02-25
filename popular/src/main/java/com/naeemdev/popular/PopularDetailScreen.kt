package com.naeemdev.popular

import androidx.compose.runtime.Composable
import com.naeemdev.common.theme.MultiModuleComposable
@Composable
fun PopularDetailScreen() {
    MultiModuleComposable(darkTheme = true) {
        PopularDetailContent()
    }
}

@Composable
fun PopularDetailContent() {
    /* do something */
}