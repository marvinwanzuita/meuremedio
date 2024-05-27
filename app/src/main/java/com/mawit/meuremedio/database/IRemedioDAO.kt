package com.mawit.meuremedio.database

import com.mawit.meuremedio.model.Remedio

interface IRemedioDAO  {

    fun salvar(remedio: Remedio): Boolean
    fun atualizar(remedio: Remedio): Boolean
    fun remover(idRemedio: Int): Boolean
    fun listar(): List<Remedio>

}