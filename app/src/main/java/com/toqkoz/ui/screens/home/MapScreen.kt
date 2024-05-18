package com.toqkoz.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.toqkoz.ui.components.Tabbar
import com.toqkoz.ui.theme.ToqkozTheme

@Composable
fun MapScreen() {
    val properties : MapProperties = MapProperties()
    val locations = listOf(
        LatLng(-34.0, 151.0), // Sydney
        LatLng(-34.0, 152.0), // San Francisco
        LatLng(-34.0, 153.0) // London
    )
    val cameraPositionState = rememberCameraPositionState()
    val boundsBuilder = LatLngBounds.Builder()
    locations.forEach { boundsBuilder.include(it) }
    val bounds = boundsBuilder.build()
    val uiSettings: MapUiSettings = MapUiSettings(zoomControlsEnabled = false)

    Column(modifier = Modifier.fillMaxSize()) {
        Tabbar(title = "Карта")
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapLoaded = {
                cameraPositionState.move(
                    CameraUpdateFactory.newLatLngBounds(bounds, 100)
                )
            },
            uiSettings = uiSettings

        ){
            locations.forEach { location ->
                Marker(
                    state = rememberMarkerState(position = location),
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE),
                    title = "Marker in ${location.toString()}"
                )
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
