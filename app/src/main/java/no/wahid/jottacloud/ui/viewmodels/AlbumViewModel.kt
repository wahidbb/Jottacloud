package no.wahid.jottacloud.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import no.wahid.jottacloud.model.Album
import no.wahid.jottacloud.model.JottaApiService


class AlbumViewModel : ViewModel() {
val apiService = JottaApiService.create()
    val album: MutableState<Album?> =  mutableStateOf(null)

    private var _isLoading by mutableStateOf(false)
    init {
        fetchAlbum()
    }
    fun fetchAlbum() {
        viewModelScope.launch {
            try {
                _isLoading = true
                val response = apiService.getAlbum()
                album.value = response.body()
                album.value?.photos?.forEach {
                    println(it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle exception, possibly updating the UI to show an error message
            } finally {
                _isLoading = false
            }
        }
    }
}
