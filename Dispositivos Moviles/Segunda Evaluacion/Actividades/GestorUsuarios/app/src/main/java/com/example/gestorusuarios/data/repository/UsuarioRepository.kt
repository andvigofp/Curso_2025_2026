package com.example.gestorusuarios.data.repository

import com.example.gestorusuarios.data.remote.RetrofitClient
import com.example.gestorusuarios.data.remote.dto.UsuarioCreateUpdateDto
import com.example.gestorusuarios.data.remote.dto.UsuarioDto
import com.example.gestorusuarios.model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * Repositorio para gestionar operaciones de usuario
 * Maneja la comunicación con la API REST
 */
class UsuarioRepository {

    private val apiService = RetrofitClient.usuarioApiService

    /**
     * Obtener todos los usuarios desde la API
     */
    suspend fun obtenerUsuarios(): Result<List<Usuario>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.obtenerUsuarios()
            if (response.isSuccessful) {
                val usuarios = response.body()?.map { it.toUsuario() } ?: emptyList()
                Result.success(usuarios)
            } else {
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Obtener un usuario específico por ID
     */
    suspend fun obtenerUsuarioPorId(id: String): Result<Usuario> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.obtenerUsuarioPorId(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it.toUsuario())
                } ?: Result.failure(Exception("Usuario no encontrado"))
            } else {
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Crear un nuevo usuario en la API
     */
    suspend fun crearUsuario(usuario: Usuario): Result<Usuario> = withContext(Dispatchers.IO) {
        try {
            val dto = UsuarioCreateUpdateDto(
                name = usuario.nombre,
                email = usuario.email,
                phone = usuario.telefono,
                address = usuario.direccion
            )
            val response = apiService.crearUsuario(dto)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it.toUsuario())
                } ?: Result.failure(Exception("Error al crear usuario"))
            } else {
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Actualizar un usuario existente
     */
    suspend fun actualizarUsuario(usuario: Usuario): Result<Usuario> = withContext(Dispatchers.IO) {
        try {
            val dto = UsuarioCreateUpdateDto(
                name = usuario.nombre,
                email = usuario.email,
                phone = usuario.telefono,
                address = usuario.direccion
            )
            val response = apiService.actualizarUsuario(usuario.id!!, dto)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it.toUsuario())
                } ?: Result.failure(Exception("Error al actualizar usuario"))
            } else {
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Eliminar un usuario
     */
    suspend fun eliminarUsuario(id: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.eliminarUsuario(id)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Extensión para convertir UsuarioDto a Usuario
     */
    private fun UsuarioDto.toUsuario(): Usuario {
        val direccionCompleta = address?.let {
            listOfNotNull(it.street, it.suite, it.city, it.zipcode)
                .filter { text -> text.isNotBlank() }
                .joinToString(", ")
        } ?: ""

        return Usuario(
            id = id,
            nombre = name,
            email = email,
            telefono = phone,
            direccion = direccionCompleta
        )
    }
}

