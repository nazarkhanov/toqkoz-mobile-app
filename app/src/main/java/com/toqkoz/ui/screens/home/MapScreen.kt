package com.toqkoz.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.toqkoz.ui.components.Tabbar
import com.toqkoz.ui.theme.ToqkozTheme

@Composable
fun MapScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Tabbar(title = "Карта")
    }
}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    ToqkozTheme {
        MapScreen()
    }
}
