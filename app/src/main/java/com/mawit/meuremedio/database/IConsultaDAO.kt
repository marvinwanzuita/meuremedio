package com.mawit.meuremedio.database

import com.mawit.meuremedio.model.Consulta

interface IConsultaDAO {

    fun salvar(consulta: Consulta):Boolean
    fun atualizar(consulta: Consulta):Boolean
    fun remover(idConsulta: Int):Boolean
    fun listar(): List<Consulta>

}