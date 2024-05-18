package com.toqkoz.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.toqkoz.R
import com.toqkoz.ui.screens.AuthScreens
import com.toqkoz.ui.theme.ToqkozTheme

@Composable
fun LoginScreen(navController: NavHostController) {
    var loginValue by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(modifier = Modifier.height(1.dp).fillMaxWidth()) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }

        Column(
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.padding(top = 12.dp, bottom = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_energy),
                    contentDescription = "ToqKoz",
                    modifier = Modifier
                        .width(14.dp)
                        .height(14.dp)
                        .padding(end = 4.dp)
                )

                Text(
                    text = "TOQKOZ",
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                )
            }

            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = "Вход",
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 24.sp)
            )

            Text(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                text = "Используйте зарегистрированный аккаунт",
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 14.sp)
            )

            Column(
                modifier = Modifier.padding(top = 40.dp)
            ) {
                OutlinedTextField(
                    value = loginValue,
                    label = {
                        Text(text = "Телефон или адрес эл. почты")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { loginValue = it }
                )

                Text(
                    modifier = Modifier.padding(top = 32.dp).clickable { navController.navigate(AuthScreens.RESET_LOGIN.name) },
                    text = "Забыли данные для входа?",
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.primary),
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.Bottom,
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 20.dp)
                    .fillMaxWidth().background(MaterialTheme.colorScheme.background),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                TextButton(
                    onClick = {
                        navController.navigate(AuthScreens.ABOUT.name)
                    }
                ) {
                    Text(text = "О системе")
                }

                Button(
                    onClick = {
                        navController.navigate(route = AuthScreens.PASSWORD.name)
                    }
                ) {
                    Text(text = "Далее")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ToqkozTheme {
        LoginScreen(rememberNavController())
    }
}
