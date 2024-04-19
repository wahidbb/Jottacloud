package no.wahid.jottacloud.util

import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import coil.compose.AsyncImage

@Composable
fun ZoomableImage(photo: String) {
    var scale by remember { mutableFloatStateOf(1f) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.pointerInput(Unit) {
            detectTransformGestures { _, _, zoom, _ ->
                scale *= zoom
                // Limit the scale to prevent the image from becoming too small or too large
                scale = scale.coerceIn(1f, 3f)
            }
        }
    ) {
        AsyncImage(
            model = photo,
            contentDescription = "Zoomable image",
            modifier = Modifier.graphicsLayer(
                scaleX = scale,
                scaleY = scale,
            )
        )
    }
}
