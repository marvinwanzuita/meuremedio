package com.mawit.meuremedio.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseUsuarioHelper(context: Context): SQLiteOpenHelper(
    context, NOME_BANCO_DADOS, null, VERSAO
) {

    companion object {
        const val NOME_BANCO_DADOS = "ListaUsuarios.db"
        const val VERSAO = 1
        const val TABELA_USUARIOS = "usuarios"
        const val COLUNA_ID_USUARIO = "id_usuario"
        const val COLUNA_NOME = "nome"
        const val COLUNA_EMAIL = "email"
        const val COLUNA_SENHA = "senha"
        const val COLUNA_IDADE = "idade"
        const val COLUNA_CONVENIO = "convenio"
        const val COLUNA_ENDERECO = "endereco"
        const val COLUNA_CUIDADOR = "cuidados"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val sql = "CREATE TABLE IF NOT EXISTS $TABELA_USUARIOS(" +
                "$COLUNA_ID_USUARIO INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "$COLUNA_NOME VARCHAR(60), " +
                "$COLUNA_EMAIL VARCHAR(100), " +
                "$COLUNA_SENHA VARCHAR(40), " +
                "$COLUNA_IDADE INTEGER, " +
                "$COLUNA_CONVENIO VARCHAR(60), " +
                "$COLUNA_ENDERECO VARCHAR(120), " +
                "$COLUNA_CUIDADOR VARCHAR(60) " +
                ");"

        try {
            db?.execSQL(sql)
            Log.i("info_db", "Sucesso ao criar tabela Usuarios")
        } catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao criar tabela Usuarios")
        }

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


}