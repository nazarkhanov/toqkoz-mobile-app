package com.toqkoz.ui.screens.home.notifications

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.toqkoz.ui.theme.ToqkozTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationDetailScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад",
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 4.dp),
        ) {
            Box(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(MaterialTheme.colorScheme.error)
                    .padding(vertical = 6.dp, horizontal = 8.dp),
            ) {
                Text(
                    text = "Неисправность",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onError
                    )
                )
            }

            Text(
                text = "#55972 РПЗ: Обесточен",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 20.sp
                )
            )

            PairInfo("Создано", "05-30-2024, 05:11:23 GTM+5")
            PairInfo("Последнее оповещение", "05-30-2024, 05:11:23 GTM+5")
            PairInfo("Детали", "Подстанция: РП3\nЭлектроснабжение: Обесточен\nАдрес: г.Алматы, ул.Момышулы 24\n")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationDetailScreenPreview() {
    ToqkozTheme {
        NotificationDetailScreen(rememberNavController())
    }
}

@Composable
fun PairInfo(key: String, value: String) {
    Column (
        modifier = Modifier.padding(vertical = 12.dp)
    ) {
        Text(
            text = key,
            modifier = Modifier
                .fillMaxWidth(),
            style = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp,
            )
        )
        Text(
            text = value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            style = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp,
            )
        )
    }
}
