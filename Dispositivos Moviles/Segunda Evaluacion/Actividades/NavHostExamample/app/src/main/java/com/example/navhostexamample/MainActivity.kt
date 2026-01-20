package com.example.navhostexamample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.navhostexamample.ui.theme.NavHostExamampleTheme
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.navhostexamample.pages.LoginScreen
import kotlinx.serialization.Serializable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHostExamampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  Box(modifier = Modifier.padding( innerPadding)) {
                      NavHost(navController = navController, startDestination = Login("")) {
                          composable<Login> { backStatEbtry ->
                              val email = backStatEbtry.toRoute<Login>().email
                              LoginScreen(navController, email)
                          }
                          composable<Grettings> {
                              Greeting("Saludos", modifier = Modifier, navController = navController)
                          }
                      }
                  }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, navController: NavController) {

    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Button({
            navController.navigate(route = Login("animacion96@gmail.com"))
        }) {
            Text("Ir a Login")
        }
    }
}