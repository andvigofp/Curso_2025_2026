package com.example.gestorusuarios.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.gestorusuarios.model.Usuario
import com.example.gestorusuarios.viewmodel.UsuarioViewModel
import com.example.gestorusuarios.ui.state.UiState
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioUsuarioScreen(
    viewModel: UsuarioViewModel,
    usuarioId: String?,
    onNavigateBack: () -> Unit
) {
    val usuario = usuarioId?.let { viewModel.obtenerUsuarioPorId(it) }
    val esEdicion = usuario != null

    var nombre by remember { mutableStateOf(usuario?.nombre ?: "") }
    var email by remember { mutableStateOf(usuario?.email ?: "") }
    var telefono by remember { mutableStateOf(usuario?.telefono ?: "") }
    var direccion by remember { mutableStateOf(usuario?.direccion ?: "") }

    var nombreError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var telefonoError by remember { mutableStateOf(false) }

    val operacionState = viewModel.operacionState.value
    val snackbarHostState = remember { SnackbarHostState() }

    // Manejar estados de operación
    LaunchedEffect(operacionState) {
        when (operacionState) {
            is UiState.Success -> {
                snackbarHostState.showSnackbar(
                    message = if (esEdicion) "Usuario actualizado" else "Usuario creado",
                    duration = SnackbarDuration.Short
                )
                viewModel.resetOperacionState()
                onNavigateBack()
            }
            is UiState.Error -> {
                snackbarHostState.showSnackbar(
                    message = operacionState.message,
                    duration = SnackbarDuration.Long
                )
                viewModel.resetOperacionState()
            }
            else -> {}
        }
    }

    fun validarFormulario(): Boolean {
        nombreError = nombre.isBlank()
        emailError = email.isBlank() || !email.contains("@")
        telefonoError = telefono.isBlank()
        return !nombreError && !emailError && !telefonoError
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (esEdicion) "Editar Usuario" else "Nuevo Usuario") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    nombre = it
                    nombreError = false
                },
                label = { Text("Nombre *") },
                modifier = Modifier.fillMaxWidth(),
                isError = nombreError,
                supportingText = {
                    if (nombreError) {
                        Text("El nombre es obligatorio")
                    }
                },
                singleLine = true,
                enabled = operacionState !is UiState.Loading
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = false
                },
                label = { Text("Email *") },
                modifier = Modifier.fillMaxWidth(),
                isError = emailError,
                supportingText = {
                    if (emailError) {
                        Text("Ingrese un email válido")
                    }
                },
                singleLine = true,
                enabled = operacionState !is UiState.Loading
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = telefono,
                onValueChange = {
                    telefono = it
                    telefonoError = false
                },
                label = { Text("Teléfono *") },
                modifier = Modifier.fillMaxWidth(),
                isError = telefonoError,
                supportingText = {
                    if (telefonoError) {
                        Text("El teléfono es obligatorio")
                    }
                },
                singleLine = true,
                enabled = operacionState !is UiState.Loading
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = direccion,
                onValueChange = { direccion = it },
                label = { Text("Dirección") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                enabled = operacionState !is UiState.Loading
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (validarFormulario()) {
                        val nuevoUsuario = Usuario(
                            nombre = nombre.trim(),
                            email = email.trim(),
                            telefono = telefono.trim(),
                            direccion = direccion.trim(),
                            id = null
                        )

                        if (esEdicion) {
                            viewModel.actualizarUsuario(nuevoUsuario)
                        } else {
                            viewModel.agregarUsuario(nuevoUsuario)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = operacionState !is UiState.Loading
            ) {
                if (operacionState is UiState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text(if (esEdicion) "Actualizar" else "Guardar")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = onNavigateBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = operacionState !is UiState.Loading
            ) {
                Text("Cancelar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "* Campos obligatorios",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
