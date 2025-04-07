package com.example.note.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.note.R
import com.example.note.navigation.Screen
import com.example.note.viewModel.NoteViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    noteViewModel: NoteViewModel,
    navController: NavController
) {
    val dataNote = LocalDate.now().toString()
    Scaffold(
       topBar = {
           TopAppBar(
               title = {
                   Column() {
                       Text("Все заметки")
                       Text("${noteViewModel.notes.size} заметок")
                   }
               }
           )
       }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(noteViewModel.notes) {
                    Card(
                        onClick = {
                            navController.navigate(route = Screen.NoteInfo.route)
                            noteViewModel.selectedNote(it.id)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(180.dp,80.dp),
                        shape = RectangleShape
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 7.dp, end = 7.dp),
                            text = it.title,
                            fontSize = 20.sp,
                        )
                        Text(
                            text = it.content,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(start = 7.dp, end = 7.dp)
                        )
                        Text(
                            text = dataNote,
                            modifier = Modifier.padding(start = 7.dp, end = 7.dp)
                        )
                    }
                }
            }
            FloatingActionButton(
                onClick = { navController.navigate(route = Screen.NoteInfo.route) },
                modifier = Modifier.align(alignment = Alignment.BottomEnd)
                    .padding(end = 12.dp, bottom = 30.dp)
                    .background(Color.Transparent)
            ) {
                Icon(
                    painter = painterResource(R.drawable.add_button),
                    modifier = Modifier.size(30.dp),
                    tint = Color.Cyan,
                    contentDescription = null
                )
            }
        }
    }
}