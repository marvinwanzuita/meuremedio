package com.mawit.meuremedio.database

import com.mawit.meuremedio.model.Usuario

interface IUsuarioDAO {

    fun salvar(usuario: Usuario): Boolean
    fun atualizar(usuario: Usuario): Boolean
    fun remover(idUsuario: Int): Boolean
    fun listar(): List<Usuario>

}