package com.example.gestorusuarios.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.State
import com.example.gestorusuarios.model.Usuario
import com.example.gestorusuarios.data.repository.UsuarioRepository
import com.example.gestorusuarios.ui.state.UiState
import kotlinx.coroutines.launch
import java.util.UUID

class UsuarioViewModel : ViewModel() {

    private val repository = UsuarioRepository()

    private val _usuarios = mutableStateListOf<Usuario>()
    val usuarios: SnapshotStateList<Usuario> = _usuarios

    // Estados de UI
    private val _uiState = mutableStateOf<UiState<List<Usuario>>>(UiState.Idle)
    val uiState: State<UiState<List<Usuario>>> = _uiState

    private val _operacionState = mutableStateOf<UiState<Unit>>(UiState.Idle)
    val operacionState: State<UiState<Unit>> = _operacionState

    // Modo de operación: true = API REST, false = Local
    private val _modoApi = mutableStateOf(true)
    val modoApi: State<Boolean> = _modoApi


    /**
     * Cambiar entre modo API y modo local
     */
    fun toggleModoApi() {
        _modoApi.value = !_modoApi.value
        if (_modoApi.value) {
            cargarUsuariosDesdeApi()
        } else {
            cargarUsuariosLocales()
        }
    }

    /**
     * Cargar usuarios desde la API REST
     */
    fun cargarUsuariosDesdeApi() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            repository.obtenerUsuarios()
                .onSuccess { listaUsuarios ->
                    _usuarios.clear()
                    _usuarios.addAll(listaUsuarios)
                    _uiState.value = UiState.Success(listaUsuarios)
                }
                .onFailure { error ->
                    _uiState.value = UiState.Error(
                        error.message ?: "Error al cargar usuarios desde la API"
                    )
                }
        }
    }

    /**
     * Cargar usuarios locales de ejemplo
     */
    private fun cargarUsuariosLocales() {
        _usuarios.clear()
        _usuarios.addAll(
            listOf(
                Usuario(
                    id = UUID.randomUUID().toString(),
                    nombre = "Juan Pérez",
                    email = "juan@example.com",
                    telefono = "123456789",
                    direccion = "Calle Principal 123"
                ),
                Usuario(
                    id = UUID.randomUUID().toString(),
                    nombre = "María García",
                    email = "maria@example.com",
                    telefono = "987654321",
                    direccion = "Avenida Central 456"
                )
            )
        )
        _uiState.value = UiState.Success(_usuarios.toList())
    }

    /**
     * Agregar usuario (API o local según modo)
     */
    fun agregarUsuario(usuario: Usuario) {
        if (_modoApi.value) {
            agregarUsuarioApi(usuario)
        } else {
            agregarUsuarioLocal(usuario)
        }
    }

    private fun agregarUsuarioLocal(usuario: Usuario) {
        _usuarios.add(usuario)
        _operacionState.value = UiState.Success(Unit)
    }

    private fun agregarUsuarioApi(usuario: Usuario) {
        viewModelScope.launch {
            _operacionState.value = UiState.Loading

            repository.crearUsuario(usuario)
                .onSuccess { nuevoUsuario ->
                    _usuarios.add(nuevoUsuario)
                    _operacionState.value = UiState.Success(Unit)
                }
                .onFailure { error ->
                    _operacionState.value = UiState.Error(
                        error.message ?: "Error al crear usuario"
                    )
                }
        }
    }

    /**
     * Actualizar usuario (API o local según modo)
     */
    fun actualizarUsuario(usuario: Usuario) {
        if (_modoApi.value) {
            actualizarUsuarioApi(usuario)
        } else {
            actualizarUsuarioLocal(usuario)
        }
    }

    private fun actualizarUsuarioLocal(usuario: Usuario) {
        val index = _usuarios.indexOfFirst { it.id == usuario.id }
        if (index != -1) {
            _usuarios[index] = usuario
            _operacionState.value = UiState.Success(Unit)
        }
    }

    private fun actualizarUsuarioApi(usuario: Usuario) {
        viewModelScope.launch {
            _operacionState.value = UiState.Loading

            repository.actualizarUsuario(usuario)
                .onSuccess { usuarioActualizado ->
                    val index = _usuarios.indexOfFirst { it.id == usuario.id }
                    if (index != -1) {
                        _usuarios[index] = usuarioActualizado
                    }
                    _operacionState.value = UiState.Success(Unit)
                }
                .onFailure { error ->
                    _operacionState.value = UiState.Error(
                        error.message ?: "Error al actualizar usuario"
                    )
                }
        }
    }

    /**
     * Eliminar usuario (API o local según modo)
     */
    fun eliminarUsuario(id: String) {
        if (_modoApi.value) {
            eliminarUsuarioApi(id)
        } else {
            eliminarUsuarioLocal(id)
        }
    }

    private fun eliminarUsuarioLocal(id: String) {
        _usuarios.removeIf { it.id == id }
        _operacionState.value = UiState.Success(Unit)
    }

    private fun eliminarUsuarioApi(id: String) {
        viewModelScope.launch {
            _operacionState.value = UiState.Loading

            repository.eliminarUsuario(id)
                .onSuccess {
                    _usuarios.removeIf { it.id == id }
                    _operacionState.value = UiState.Success(Unit)
                }
                .onFailure { error ->
                    _operacionState.value = UiState.Error(
                        error.message ?: "Error al eliminar usuario"
                    )
                }
        }
    }

    /**
     * Obtener usuario por ID
     */
    fun obtenerUsuarioPorId(id: String): Usuario? {
        return _usuarios.find { it.id == id }
    }

    /**
     * Resetear estado de operación
     */
    fun resetOperacionState() {
        _operacionState.value = UiState.Idle
    }
}
