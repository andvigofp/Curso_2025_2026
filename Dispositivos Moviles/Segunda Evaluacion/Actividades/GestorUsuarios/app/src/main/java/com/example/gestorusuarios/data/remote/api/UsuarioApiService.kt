package com.example.gestorusuarios.data.remote.api

import com.example.gestorusuarios.data.remote.dto.UsuarioCreateUpdateDto
import com.example.gestorusuarios.data.remote.dto.UsuarioDto
import retrofit2.Response
import retrofit2.http.*

/**
 * Interfaz de API REST para gestión de usuarios
 * Usando JSONPlaceholder como API de prueba
 */
interface UsuarioApiService {

    /**
     * Obtener todos los usuarios
     */
    @GET("json/users")
    suspend fun obtenerUsuarios(): Response<List<UsuarioDto>>

    /**
     * Obtener un usuario por ID
     */
    @GET("json/users/{id}")
    suspend fun obtenerUsuarioPorId(@Path("id") id: String): Response<UsuarioDto>

    /**
     * Crear un nuevo usuario
     */
    @PUT("json/users")
    suspend fun crearUsuario(@Body usuario: UsuarioCreateUpdateDto): Response<UsuarioDto>

    /**
     * Actualizar un usuario existente
     */
    @PUT("json/users/{id}")
    suspend fun actualizarUsuario(
        @Path("id") id: String,
        @Body usuario: UsuarioCreateUpdateDto
    ): Response<UsuarioDto>

    /**
     * Eliminar un usuario
     */
    @DELETE("json/users/{id}")
    suspend fun eliminarUsuario(@Path("id") id: String): Response<Unit>
}
