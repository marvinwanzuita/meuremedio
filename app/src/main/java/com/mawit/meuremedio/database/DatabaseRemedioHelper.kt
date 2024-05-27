package com.mawit.meuremedio.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseRemedioHelper(context: Context) : SQLiteOpenHelper(
    context, NOME_BANCO_DADOS, null, VERSAO
) {

    companion object {
        const val NOME_BANCO_DADOS = "MeuRemedio.db"
        const val VERSAO = 1
        const val TABELA_REMEDIOS = "remedios"
        const val COLUNA_ID_REMEDIO = "id_remedio"
        const val COLUNA_NOME_REMEDIO = "nome_remedio"
        const val COLUNA_COMPONENTES = "componentes"
        const val COLUNA_HORARIO_USO = "horario_uso"
        const val COLUNA_PRECAUCOES = "precaucoes"
        const val COLUNA_OBSERVACOES = "observacoes"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE IF NOT EXISTS $TABELA_REMEDIOS ( " +
                "$COLUNA_ID_REMEDIO INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "$COLUNA_NOME_REMEDIO VARCHAR(50), " +
                "$COLUNA_COMPONENTES VARCHAR(200), " +
                "$COLUNA_HORARIO_USO VARCHAR(10), " +
                "$COLUNA_PRECAUCOES VARCHAR(200), " +
                "$COLUNA_OBSERVACOES VARCHAR(200) " +
                ");"

        try {
            db?.execSQL(sql)
            Log.i("info_db", "Sucesso ao criar a tabela")
        } catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao criar a tabela")
        }


    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}