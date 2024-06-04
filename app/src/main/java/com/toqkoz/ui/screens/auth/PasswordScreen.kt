package com.toqkoz.ui.screens.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.toqkoz.MainScreens
import com.toqkoz.MyApi
import com.toqkoz.MyViewModel
import com.toqkoz.R
import com.toqkoz.data.AuthRequest
import com.toqkoz.data.LoginStatus
import com.toqkoz.data.NotificationData
import com.toqkoz.data.TokenData
import com.toqkoz.data.TrackerData
import com.toqkoz.ui.screens.AuthScreens
import com.toqkoz.ui.theme.ToqkozTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@OptIn(DelicateCoroutinesApi::class)
@Composable
fun PasswordScreen(rootNavController: NavHostController, authNavController: NavHostController, viewModel: MyViewModel) {
    var isShowPasswordChecked by remember { mutableStateOf(false) }
    val passwordValue by viewModel.passwordValue.collectAsState()
    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    val loginStatus by viewModel.loginStatus.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()) {
            if(loginStatus == LoginStatus.LOADING.name){
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
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
                text = "Добро пожаловать!",
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 24.sp)
            )

            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ){
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Пользователь",
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                )

                Text(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 8.dp),
                    text = viewModel.loginValue.value,
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 14.sp)
                )
            }

            Column(
                modifier = Modifier.padding(top = 40.dp)
            ) {
                OutlinedTextField(
                    value = passwordValue,
                    label = {
                        Text(text =
                        if (loginStatus == LoginStatus.WRONGCREDENTIALS.name){
                            "Неверный логин или пароль"
                        } else{"Введите пароль"})
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    onValueChange = { viewModel.passwordValue.value = it },
                    isError = loginStatus == LoginStatus.WRONGCREDENTIALS.name,
                    visualTransformation = if (isShowPasswordChecked) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    }
                )

                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) { isShowPasswordChecked = !isShowPasswordChecked },

                ) {
                    Checkbox(checked = isShowPasswordChecked, onCheckedChange = {
                        isShowPasswordChecked= !isShowPasswordChecked
                    }, modifier = Modifier.absoluteOffset((-12).dp))
                    Text(
                        text = "Показать пароль",
                        textAlign = TextAlign.Center,
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal),
                        modifier = Modifier.absoluteOffset((-12).dp)
                    )
                }

//                Text(
//                    modifier = Modifier
//                        .padding(top = 32.dp)
//                        .clickable { authNavController.navigate(AuthScreens.RESET_PASSWORD.name) },
//                    text = "Забыли пароль от аккаунта?",
//                    textAlign = TextAlign.Center,
//                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.primary),
//                )
            }
        }

        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.Bottom,
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 20.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                TextButton(
                    onClick = {
                        viewModel.loginStatus.value = "Logging"
                        authNavController.popBackStack()

                    }
                ) {
                    Text(text = "Назад")
                }

                Button(
                    onClick = {
                        viewModel.loginStatus.value = LoginStatus.LOADING.name
                        val authRequest = AuthRequest(email = viewModel.loginValue.value, password = passwordValue)

                        viewModel.signin(authRequest)

                    }
                ) {
                    Text(text = "Далее")
                }
                if(loginStatus == LoginStatus.LOGGEDIN.name){
                    rootNavController.navigate(route = MainScreens.HOME.name)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}



@Preview(showBackground = true)
@Composable
fun PasswordScreenPreview() {
    ToqkozTheme {
//        PasswordScreen(rememberNavController(), rememberNavController(), viewModel = MyViewModel())
    }
}
