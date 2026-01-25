package com.example.gestorusuarios.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gestorusuarios.ui.screens.FormularioUsuarioScreen
import com.example.gestorusuarios.ui.screens.ListaUsuariosScreen
import com.example.gestorusuarios.viewmodel.UsuarioViewModel

sealed class Screen(val route: String) {
    object ListaUsuarios : Screen("lista_usuarios")
    object FormularioUsuario : Screen("formulario_usuario/{usuarioId}") {
        fun createRoute(usuarioId: String?) = "formulario_usuario/${usuarioId ?: "nuevo"}"
    }
}

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: UsuarioViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ListaUsuarios.route
    ) {
        composable(Screen.ListaUsuarios.route) {
            ListaUsuariosScreen(
                viewModel = viewModel,
                onNuevoUsuario = {
                    navController.navigate(Screen.FormularioUsuario.createRoute(null))
                },
                onEditarUsuario = { usuarioId ->
                    navController.navigate(Screen.FormularioUsuario.createRoute(usuarioId))
                }
            )
        }

        composable(
            route = Screen.FormularioUsuario.route,
            arguments = listOf(
                navArgument("usuarioId") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { backStackEntry ->
            val usuarioId = backStackEntry.arguments?.getString("usuarioId")
            val esNuevo = usuarioId == "nuevo"

            FormularioUsuarioScreen(
                viewModel = viewModel,
                usuarioId = if (esNuevo) null else usuarioId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
