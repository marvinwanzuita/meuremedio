package com.mawit.meuremedio.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.mawit.meuremedio.model.Usuario

class UsuarioDAO(context: Context) : IUsuarioDAO {

    private val escrita = DatabaseUsuarioHelper(context).writableDatabase
    private val leitura = DatabaseUsuarioHelper(context).readableDatabase

    override fun salvar(usuario: Usuario): Boolean {

        val conteudos = ContentValues()
        conteudos.put(DatabaseUsuarioHelper.COLUNA_NOME, usuario.nome)
        conteudos.put(DatabaseUsuarioHelper.COLUNA_EMAIL, usuario.email)
        conteudos.put(DatabaseUsuarioHelper.COLUNA_SENHA, usuario.senha)
        conteudos.put(DatabaseUsuarioHelper.COLUNA_IDADE, usuario.idade)
        conteudos.put(DatabaseUsuarioHelper.COLUNA_ENDERECO, usuario.endereço)
        conteudos.put(DatabaseUsuarioHelper.COLUNA_CONVENIO, usuario.convenio)
        conteudos.put(DatabaseUsuarioHelper.COLUNA_CUIDADOR, usuario.cuidador)

        try {
            escrita.insert(
                DatabaseUsuarioHelper.TABELA_USUARIOS,
                null,
                conteudos
            )
            Log.i("info_db", "Sucesso ao criar tabela Usuario")
        } catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao criar tabela Usuario")
            return false
        }

        return true
    }

    override fun atualizar(usuario: Usuario): Boolean {

        val args = arrayOf(usuario.id.toString())

        val conteudo = ContentValues()
        conteudo.put(DatabaseUsuarioHelper.COLUNA_NOME, usuario.nome)
        conteudo.put(DatabaseUsuarioHelper.COLUNA_EMAIL, usuario.email)
        conteudo.put(DatabaseUsuarioHelper.COLUNA_SENHA, usuario.senha)
        conteudo.put(DatabaseUsuarioHelper.COLUNA_IDADE, usuario.idade)
        conteudo.put(DatabaseUsuarioHelper.COLUNA_CONVENIO, usuario.convenio)
        conteudo.put(DatabaseUsuarioHelper.COLUNA_ENDERECO, usuario.endereço)
        conteudo.put(DatabaseUsuarioHelper.COLUNA_CUIDADOR, usuario.cuidador)


        try {
            escrita.update(
                DatabaseUsuarioHelper.TABELA_USUARIOS,
                conteudo,
                "${DatabaseUsuarioHelper.COLUNA_ID_USUARIO} = ?",
                args
            )
            Log.i("info_db", "Sucesso ao atualizar usuario")

        } catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao atualizar usuario")
            return false
        }

        return true
    }

    override fun remover(idUsuario: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun listar(): List<Usuario> {

        val listaTarefas = mutableListOf<Usuario>()

        val sql = "SELECT * FROM ${DatabaseUsuarioHelper.TABELA_USUARIOS}"

        val cursor = leitura.rawQuery(sql, null)

        val indiceIdUsuario = cursor.getColumnIndex(DatabaseUsuarioHelper.COLUNA_ID_USUARIO)
        val indiceNome = cursor.getColumnIndex(DatabaseUsuarioHelper.COLUNA_NOME)
        val indiceEmail = cursor.getColumnIndex(DatabaseUsuarioHelper.COLUNA_EMAIL)
        val indiceSenha = cursor.getColumnIndex(DatabaseUsuarioHelper.COLUNA_SENHA)
        val indiceIdade = cursor.getColumnIndex(DatabaseUsuarioHelper.COLUNA_IDADE)
        val indiceConvenio = cursor.getColumnIndex(DatabaseUsuarioHelper.COLUNA_CONVENIO)
        val indiceEndereco = cursor.getColumnIndex(DatabaseUsuarioHelper.COLUNA_ENDERECO)
        val indiceCuidador = cursor.getColumnIndex(DatabaseUsuarioHelper.COLUNA_CUIDADOR)

        while (cursor.moveToNext()){

            val idUsuario = cursor.getInt(indiceIdUsuario)
            val nome = cursor.getString(indiceNome)
            val email = cursor.getString(indiceEmail)
            val senha = cursor.getString(indiceSenha)
            val idade = cursor.getInt(indiceIdade)
            val convenio = cursor.getString(indiceConvenio)
            val endereco = cursor.getString(indiceEndereco)
            val cuidador = cursor.getString(indiceCuidador)

            listaTarefas.add(
                Usuario(idUsuario, nome, email, senha, idade, convenio, endereco, cuidador)
            )

        }

        return listaTarefas
    }
}