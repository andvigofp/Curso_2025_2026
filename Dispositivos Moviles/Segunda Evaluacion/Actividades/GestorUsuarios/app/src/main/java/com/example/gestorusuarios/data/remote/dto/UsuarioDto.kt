package com.example.gestorusuarios.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO para Usuario desde la API
 * Compatible con JSONPlaceholder API (https://jsonplaceholder.typicode.com/)
 */
data class UsuarioDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("phone")
    val phone: String,

    @SerializedName("address")
    val address: AddressDto? = null,

    @SerializedName("username")
    val username: String? = null,

    @SerializedName("website")
    val website: String? = null
)

data class AddressDto(
    @SerializedName("street")
    val street: String? = null,

    @SerializedName("suite")
    val suite: String? = null,

    @SerializedName("city")
    val city: String? = null,

    @SerializedName("zipcode")
    val zipcode: String? = null
)

/**
 * DTO para crear/actualizar usuario
 */
data class UsuarioCreateUpdateDto(
    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("phone")
    val phone: String,

    @SerializedName("address")
    val address: String? = null
)
