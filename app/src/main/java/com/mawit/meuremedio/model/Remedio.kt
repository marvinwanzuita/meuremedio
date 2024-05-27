package com.mawit.meuremedio.model

import java.io.Serializable

data class Remedio(
    val idRemedio: Int,
    val nomeRemedio: String,
    val componentes: String,
    val horarioUso : String,
    val precaucoes : String,
    val observacoes : String
) : Serializable