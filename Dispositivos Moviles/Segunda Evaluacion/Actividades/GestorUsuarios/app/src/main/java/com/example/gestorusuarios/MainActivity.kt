package com.example.gestorusuarios

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.gestorusuarios.navigation.NavGraph
import com.example.gestorusuarios.ui.theme.GestorUsuariosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GestorUsuariosTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
