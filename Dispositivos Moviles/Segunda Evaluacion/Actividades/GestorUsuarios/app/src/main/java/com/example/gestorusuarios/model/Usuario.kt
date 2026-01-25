package com.example.gestorusuarios.model

data class Usuario(
    val id: String?,
    val nombre: String,
    val email: String,
    val telefono: String,
    val direccion: String = ""
)
