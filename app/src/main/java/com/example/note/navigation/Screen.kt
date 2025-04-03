package com.example.note.navigation

sealed class Screen(val route: String){
    data object NoteHome : Screen(route = "note_home")
    data object NoteInfo: Screen(route = "note_info")
}