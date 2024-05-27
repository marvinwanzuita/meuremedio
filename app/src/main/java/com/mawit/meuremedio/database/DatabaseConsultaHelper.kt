package com.mawit.meuremedio.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseConsultaHelper(context: Context) : SQLiteOpenHelper(
    context, NOME_BANCO_DADOS_CONSULTAS, null, VERSAO
) {

    companion object {
        const val NOME_BANCO_DADOS_CONSULTAS = "ListaConsultas.db"
        const val VERSAO = 1
        const val TABELA_CONSULTAS = "consultas"
        const val COLUNA_ID_CONSULTA = "id_consulta"
        const val COLUNA_DESCRICAO = "descricao"
        const val COLUNA_DATA_CONSULTA = "data_consulta"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val sql = "CREATE TABLE IF NOT EXISTS $TABELA_CONSULTAS(" +
                "$COLUNA_ID_CONSULTA INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "$COLUNA_DESCRICAO VARCHAR(100), " +
                "$COLUNA_DATA_CONSULTA VARCHAR(20)" +
                ");"
        
        try {
            db?.execSQL(sql)
            Log.i("info_db", "Sucesso ao criar a tabela Consultas")
        } catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao criar a tabela Consultas")
        }
        
        
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}