package no.wahid.jottacloud.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import no.wahid.jottacloud.ui.screens.AlbumsScreen
import no.wahid.jottacloud.ui.screens.PhotoDetailScreen
import no.wahid.jottacloud.ui.viewmodels.AlbumViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "photoGrid") {
        composable("photoGrid") {
            // Assuming AlbumViewModel is injected or retrieved here
            val viewModel: AlbumViewModel = viewModel()
            AlbumsScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            "photoDetail/{photoIndex}", arguments = listOf(
                navArgument("photoIndex") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val photoIndex = backStackEntry.arguments?.getString("photoIndex") ?: ""
            PhotoDetailScreen(photoIndex.toInt(), navController = navController)
        }
    }
}
