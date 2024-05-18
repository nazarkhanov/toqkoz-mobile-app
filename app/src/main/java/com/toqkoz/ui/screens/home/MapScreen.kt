package com.toqkoz.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.toqkoz.R
import com.toqkoz.ui.components.Tabbar
import com.toqkoz.ui.theme.ToqkozTheme


data class Station(
    val id: Int,
    val address: String,
    val latLng: LatLng,
    val status: String
)

@SuppressLint("ResourceAsColor")
@Composable
fun MapScreen() {
    MapProperties()

    //list of stations to be displayed
    val stations = listOf(
        Station(id = 1,
            address = "Город Алматы,Бостандыкский район, ул.Торайгырова 19",
            latLng = LatLng( 43.202905,76.876543),
            status = "working"
        ),
        Station(id = 1,
            address = "Город Алматы,Бостандыкский район, ул.Торайгырова 20",
            latLng = LatLng( 43.200618 ,76.873510),
            status = "not working"
        ),
        Station(id = 1,
            address = "Город Алматы,Бостандыкский район, ул.Торайгырова 21",
            latLng = LatLng(43.200936,76.874434),
            status = "processing"
        )
    )

    val cameraPositionState = rememberCameraPositionState {
        val latitude = 43.20419
        val longitude = 76.8741537
        val zoom = 12.0f
        position = CameraPosition.fromLatLngZoom(LatLng(latitude.toDouble(), longitude.toDouble()), zoom.toFloat())
    }
    val boundsBuilder = LatLngBounds.Builder()
    stations.forEach { boundsBuilder.include(it.latLng) }
    val bounds = boundsBuilder.build()
    val uiSettings = MapUiSettings(zoomControlsEnabled = false)


    Column(modifier = Modifier.fillMaxSize()) {
        Tabbar(title = "Карта")

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = uiSettings,
        ){

            stations.forEach { station ->
                
                MarkerInfoWindow(
                    state = rememberMarkerState(position = station.latLng),
                    title = station.address,
                    icon = when (station.status) {
                        "working" -> {
                            try {
                                BitmapDescriptorFactory.fromResource(R.drawable.working_frame)
                            } catch (e: Exception) {
                                // Handle error when loading bitmap
                                null
                            }
                        }
                        "not working" -> {
                            try {
                                BitmapDescriptorFactory.fromResource(R.drawable.not_working_frame)
                            } catch (e: Exception) {
                                // Handle error when loading bitmap
                                null
                            }
                        }
                        "processing" -> {
                            try {
                                BitmapDescriptorFactory.fromResource(R.drawable.processing)
                            } catch (e: Exception) {
                                // Handle error when loading bitmap
                                null
                            }
                        }
                        else -> null // Handle default case or provide a fallback icon
                    }
                ){
                    marker ->
                    Box(
                        modifier = Modifier
                            .border(
                                BorderStroke(0.5.dp, Color(R.color.grey)),
                                shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp)
                            )
                            .background(
                                color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp),

                                )
                        ,
                    ) {


                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = station.id.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(top = 10.dp)
                                    .fillMaxWidth(),
                                style = MaterialTheme.typography.headlineSmall,

                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            //.........................Text : description
                            Text(
                                text = station.address,
                                textAlign =TextAlign.Center,
                                modifier = Modifier
                                    .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                                    .fillMaxWidth(),
                                style = MaterialTheme.typography.bodyLarge,
                            )
                            //.........................Spacer
                            Spacer(modifier = Modifier.height(24.dp))
                            
                            //
                            
                            Button(onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(200,200,200)),

                                ) {

                                Text(text = "Подробнее", fontSize = 12.sp)
                            }

                        }

                    }
                }
            }


        }


    }

}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    ToqkozTheme {
        MapScreen()
    }
}
