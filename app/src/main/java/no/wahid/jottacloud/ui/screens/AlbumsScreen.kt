package no.wahid.jottacloud.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import no.wahid.jottacloud.R
import no.wahid.jottacloud.model.Photo
import no.wahid.jottacloud.ui.viewmodels.AlbumViewModel
import no.wahid.jottacloud.util.PhotosHolder
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.SortedMap

@Composable
fun AlbumsScreen(navController: NavController, viewModel: AlbumViewModel) {
    val photosList = viewModel.album.value?.photos.orEmpty()
    val groupedByDate = photosList.groupBy { photo ->
        val timestampInMillis = (photo.timestamp?.toLong() ?: 0) / 1_000_000
        Instant.ofEpochMilli(timestampInMillis).atZone(ZoneId.systemDefault()).toLocalDate()
    }.toSortedMap(reverseOrder()) // Sorts dates in descending order

    val allPhotosIndexed = groupedByDate.values.flatten()
    var columns by remember { mutableStateOf(3) }  // Default to 3 columns, adjustable by user

    Column {
        ColumnAdjuster(currentCount = columns) { newCount ->
            columns = newCount  // Update the number of columns based on user interaction
        }
        PhotoGrid(groupedByDate, columns, allPhotosIndexed, navController)
    }
}

@Composable
fun ColumnAdjuster(currentCount: Int, updateColumns: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()  // Stretch the Row to fill the maximum width
            .padding(8.dp), // Padding around the Row
        horizontalArrangement = Arrangement.End  // Align content to the end (right side)
    ) {
        IconButton(
            onClick = {
                // Calculate the next grid size and wrap around after 4
                val nextCount = if (currentCount >= 4) 1 else currentCount + 1
                updateColumns(nextCount)
            }
        ) {
            // Use a custom icon from the resources
            Icon(
                painter = painterResource(id = R.drawable.grid_view),  // Custom grid icon resource
                contentDescription = "Adjust Grid"  // Provide accessibility description
            )
        }
    }
}

@Composable
fun PhotoGrid(
    groupedByDate: SortedMap<LocalDate, List<Photo>>,
    columns: Int,
    allPhotosIndexed: List<Photo>,
    navController: NavController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier.padding(8.dp),
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        groupedByDate.forEach { (date, photos) ->
            item(span = { GridItemSpan(maxLineSpan) }) {
                Text(
                    text = date.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 0.dp)
                )
            }
            items(photos) { photo ->
                AlbumItem(photo = photo, onClick = {
                    val globalIndex = allPhotosIndexed.indexOf(photo)
                    PhotosHolder.photoslist = allPhotosIndexed
                    navController.navigate("photoDetail/$globalIndex")
                })
            }
        }
    }
}


@Composable
fun AlbumItem(photo: Photo, onClick: () -> Unit) {
    AsyncImage(
        model = photo.thumbnailUrl + ".m",
        contentDescription = "Photo Thumbnail",
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable(onClick = onClick)
    )
}
