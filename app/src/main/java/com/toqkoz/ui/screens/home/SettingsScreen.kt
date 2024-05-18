package com.toqkoz.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.toqkoz.ui.components.Tabbar
import com.toqkoz.ui.theme.ToqkozTheme

@Composable
fun SettingsScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Tabbar(title = "Настройки")
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    ToqkozTheme {
        SettingsScreen()
    }
}
