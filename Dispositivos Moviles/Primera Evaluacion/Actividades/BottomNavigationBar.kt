package com.example.spottyeduardoandres.myComponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BottomNavigationBar(
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onLibraryClick: () -> Unit
) {
    NavigationBar(containerColor = Color.Black) {
        NavigationBarItem(
            selected = true,
            onClick = onHomeClick,
            icon = { Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.White) },
            label = { Text("Home", color = Color.White) }
        )
        NavigationBarItem(
            selected = false,
            onClick = onSearchClick,
            icon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)},
            label = { Text("Search", color = Color.White)}
        )
        NavigationBarItem(
            selected = false,
            onClick = onLibraryClick,
            icon = { Icon(Icons.Default.LibraryBooks, contentDescription = "Library", tint = Color.White)},
            label = { Text("Your Library", color = Color.White)}
        )
    }
}
