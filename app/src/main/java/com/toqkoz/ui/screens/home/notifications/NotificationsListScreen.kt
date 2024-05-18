package com.toqkoz.ui.screens.home.notifications

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.toqkoz.ui.components.Tabbar
import com.toqkoz.ui.screens.home.NotificationsScreens
import com.toqkoz.ui.theme.ToqkozTheme

@Composable
fun NotificationsListScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Tabbar(title = "Оповещения")

        Column (
            modifier = Modifier
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(vertical = 32.dp)
        ) {
            OutlinedCard (
                onClick = { navController.navigate(NotificationsScreens.DETAIL.name) },
                modifier = Modifier
                    .padding(top = 0.dp, bottom = 32.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            ) {
                Column (
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
                        .fillMaxWidth()
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
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
                            text = "10 минут назад",
                            style = TextStyle(fontSize = 12.sp)
                        )
                    }

                    Column {
                        Text(
                            text = "#55972 РПЗ: Обесточен",
                            modifier = Modifier.padding(vertical = 8.dp),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 18.sp
                            )
                        )

                        Text(
                            text = "12 оповещений",
                            modifier = Modifier.padding(bottom = 8.dp),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 12.sp
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationsListScreenPreview() {
    ToqkozTheme {
        NotificationsListScreen(rememberNavController())
    }
}
