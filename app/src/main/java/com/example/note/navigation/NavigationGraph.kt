package com.example.note.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.note.model.NoteModel
import com.example.note.view.HomeView
import com.example.note.view.InfoView
import com.example.note.viewModel.NoteViewModel

@Composable
fun NavigationGraph(
    navHostController: NavHostController,
    viewModel: NoteViewModel,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.NoteHome.route
    ) {
        composable(
            route = Screen.NoteHome.route
        ) {
            HomeView(
                noteViewModel = viewModel,
                navController = navHostController
            )
        }
        composable(
            route = Screen.NoteInfo.route
        ) {
            InfoView(
                navController = navHostController,
                noteViewModel = viewModel,
            )
        }
    }
}