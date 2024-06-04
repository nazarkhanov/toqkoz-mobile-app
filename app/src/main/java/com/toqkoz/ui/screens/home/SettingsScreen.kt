package com.toqkoz.ui.screens.home

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import com.toqkoz.MyViewModel
import com.toqkoz.ui.components.Tabbar
import com.toqkoz.ui.screens.HomeScreens
import com.toqkoz.ui.theme.ToqkozTheme


@Composable
fun SettingsScreen(rootNavController: NavHostController, homeNavController: NavHostController, viewModel: MyViewModel) {
    val context = LocalContext.current
    val loginStatus by viewModel.loginStatus.collectAsState()
    Column {
        Tabbar(title = "Настройки")

        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 32.dp, horizontal = 16.dp)) {
            Text(text = "Аккаунт", fontSize = 22.sp, fontWeight = FontWeight.Medium,
                color = Color.Gray, modifier = Modifier.padding(start = 10.dp))
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedCard (
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            ){
                Column(modifier = Modifier.padding(vertical = 16.dp)){
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        
                    ){
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Пользователь",
                            modifier = Modifier
                                .width(25.dp)
                                .height(25.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))

                        Column(verticalArrangement = Arrangement.Center) {
                            Text(
                                modifier = Modifier
                                    .padding(vertical = 8.dp, horizontal = 8.dp),
                                text = viewModel.currentUser.first_name +" "+ viewModel.currentUser.last_name,
                                textAlign = TextAlign.Center,
                                style = TextStyle(fontSize = 18.sp)
                            )
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp),
                                text = viewModel.currentUser.email,
                                textAlign = TextAlign.Center,
                                style = TextStyle(fontSize = 16.sp),
                                color = Color.Gray
                            )
                        }
                    }
                }

            }

            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "O нас", fontSize = 22.sp, fontWeight = FontWeight.Medium,
                color = Color.Gray, modifier = Modifier.padding(start = 10.dp))
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedCard (
                onClick = {
                    homeNavController.navigate(route = HomeScreens.ABOUT.name)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            ){
                Column(){
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(vertical = 16.dp)

                        ){
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "Информация",
                            modifier = Modifier
                                .width(25.dp)
                                .height(25.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))

                        Column(verticalArrangement = Arrangement.Center) {
                            Text(
                                modifier = Modifier
                                    .padding(vertical = 8.dp, horizontal = 8.dp),
                                text = "О системе",
                                textAlign = TextAlign.Center,
                                style = TextStyle(fontSize = 18.sp)
                            )
                        }
                    }


                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedCard (
                onClick = {
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.setType("text/html")
                    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("salem@toqkoz.kz"))
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Обратная связь")
                    intent.putExtra(Intent.EXTRA_TEXT, "Здравствуйте! Я пользователь Toqkoz ...")
                    if (intent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(intent)
                    } else {
                        // Handle case where the target app is not installed
                        Toast.makeText(context, "Приложение не установлено", Toast.LENGTH_SHORT).show()
                    }
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(vertical = 16.dp)

                ){
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Обратная связь",
                        modifier = Modifier
                            .width(25.dp)
                            .height(25.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))

                    Column(verticalArrangement = Arrangement.Center) {
                        Text(
                            modifier = Modifier
                                .padding(vertical = 8.dp, horizontal = 8.dp),
                            text = "Связь",
                            textAlign = TextAlign.Center,
                            style = TextStyle(fontSize = 18.sp)
                        )
                    }
                }
            }



            Spacer(modifier = Modifier.height(32.dp))
            OutlinedCard (
                onClick = { viewModel.signOut() },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            ) {
                Column() {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(vertical = 16.dp)

                    ) {
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            imageVector = Icons.Filled.ExitToApp,
                            contentDescription = "Выйти",
                            modifier = Modifier
                                .width(25.dp)
                                .height(25.dp),
                            tint = Color.Red
                        )
                        Spacer(modifier = Modifier.width(16.dp))

                        Column(verticalArrangement = Arrangement.Center) {
                            Text(
                                modifier = Modifier
                                    .padding(vertical = 8.dp, horizontal = 8.dp),
                                text = "Выйти",
                                textAlign = TextAlign.Center,
                                style = TextStyle(fontSize = 18.sp),
                                color = Color.Red
                            )
                        }
                    }

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    ToqkozTheme {
//        SettingsScreen(viewModel = MyViewModel())
    }
}
