package com.toqkoz.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.toqkoz.MainScreens
import com.toqkoz.MyViewModel
import com.toqkoz.data.LoginStatus
import com.toqkoz.ui.components.Tabbar
import com.toqkoz.ui.screens.home.notifications.PairInfo
import com.toqkoz.ui.theme.ToqkozTheme

@Composable
fun SettingsScreen(rootNavController: NavHostController, viewModel: MyViewModel) {
    val loginStatus by viewModel.loginStatus.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        Tabbar(title = "Настройки")
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding()
                .padding(horizontal = 20.dp, vertical = 4.dp),
        ) {


            Text(
                text = viewModel.currentUser.first_name+ " " + viewModel.currentUser.last_name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            PairInfo("Почта: ", viewModel.currentUser.email)
            PairInfo("Должность: ", viewModel.currentUser.position)
            Spacer(modifier = Modifier.height(36.dp))
            Button(onClick = { viewModel.signOut()}) {
                Text(text = "Выйти")
            }
            if (loginStatus == LoginStatus.LOGGING.name){
                rootNavController.navigate(route = MainScreens.AUTH.name)
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
