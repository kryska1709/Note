package com.example.note.view

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.note.R
import com.example.note.navigation.Screen
import com.example.note.viewModel.NoteViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoView(
    navController: NavController,
    noteViewModel: NoteViewModel
) {
    val select = noteViewModel.selectedIdNote.value
    val titleNote = remember { mutableStateOf(select?.title ?: "") }
    val contentNote = remember { mutableStateOf(select?.content ?: "") }
    val dataNote = LocalDate.now().toString()
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.background(Color.Black),
        topBar = {
            TopAppBar(
                modifier = Modifier.background(Color.Black),
                title = {
                    TextField(
                        value = titleNote.value,
                        onValueChange = {
                            titleNote.value = it
                        },
                        label = {
                            Text("Заголовок")
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {navController.navigate(route = Screen.NoteHome.route)}
                    ){
                        Icon(
                            painter = painterResource(R.drawable.back_112351),
                            tint = Color.Cyan,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            select?.let {
                                noteViewModel.deletedNote(select)
                                navController.navigate(route = Screen.NoteHome.route)
                            } ?:
                                Toast.makeText(context,"вы ещё не сохранили заметку", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.trashcan_delete_remove_trash_icon_178327),
                            tint = Color.Cyan,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .imePadding()
            ) {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = dataNote
                )
                TextField(
                    modifier = Modifier.fillMaxSize(),
                    value = contentNote.value,
                    onValueChange = {
                        contentNote.value = it
                    },
                    label = {
                        Text(
                            text = "Ваши заметки"
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
            FloatingActionButton(
                onClick = {
                    if(titleNote.value == "" && contentNote.value == ""){
                            Toast.makeText(context, "вы ничего не написали", Toast.LENGTH_SHORT)
                                .show()
                    } else if(contentNote.value  == ""){
                        Toast.makeText(context, "ваша заметка пуста", Toast.LENGTH_SHORT)
                            .show()
                    }
                    else if (titleNote.value == "") {
                        Toast.makeText(context, "заполните поле заголовка", Toast.LENGTH_SHORT)
                            .show()
                    }
                    else {
                        noteViewModel.addNote(titleNote.value, contentNote.value,dataNote)
                        navController.navigate(route = Screen.NoteHome.route)
                    }
                },
                modifier = Modifier
                    .align(alignment = Alignment.BottomEnd)
                    .padding(end = 12.dp, bottom = 16.dp)
                    .background(Color.Transparent)
            ) {
                Icon(
                    painter = painterResource(R.drawable.icons8________),
                    tint = Color.Cyan,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}