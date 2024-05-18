package com.toqkoz.ui.screens.auth

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
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
import com.toqkoz.ui.theme.ToqkozTheme

@Composable
fun AboutScreen(navController: NavHostController) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
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
                text = "О нас",
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 24.sp)
            )

            Column {
                Text(
                    modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth(),
                    text = "Удаленная система мониторинга, позволяющая получать оповещения в реальном времени при возникновении инцидентов.",
                    style = TextStyle(fontSize = 14.sp)
                )

                Text(
                    modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth(),
                    text = "Система состоит из трех основных компонентов: \n\n– устройста слежения\n" +
                            "– информационная система\n– мобильное приложение\n\nПо всем возникающим вопросам и сотрудничеству, просим написать нам на почту:",
                    style = TextStyle(fontSize = 14.sp)
                )

                Text(
                    modifier = Modifier.clickable {
                        context.sendMail(to = "salem@toqkoz.kz")
                    },
                    text = "salem@toqkoz.kz",
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.primary),
                )
            }
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
                    navController.popBackStack()
                }
            ) {
                Text(text = "Назад")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    ToqkozTheme {
        AboutScreen(rememberNavController())
    }
}

fun Context.sendMail(to: String, subject: String = "") {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "vnd.android.cursor.item/email" // or "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        // TODO: Handle case where no email app is available
    } catch (t: Throwable) {
        // TODO: Handle potential other type of exceptions
    }
}

fun Context.dial(phone: String) {
    try {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        startActivity(intent)
    } catch (t: Throwable) {
        // TODO: Handle potential exceptions
    }
}
