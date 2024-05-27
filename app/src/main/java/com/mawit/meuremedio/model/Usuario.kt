package com.mawit.meuremedio.model

import java.io.Serializable

data class Usuario(
    val id: Int,
    val nome: String?,
    val email: String,
    val senha: String,
    val idade: Int?,
    val convenio: String?,
    val endereço: String?,
    val cuidador: String?
) : Serializable
