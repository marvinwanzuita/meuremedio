package com.mawit.meuremedio.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.mawit.meuremedio.model.Consulta

class ConsultaDAO(context: Context) : IConsultaDAO {

    private val escrita = DatabaseConsultaHelper(context).writableDatabase
    private val leitura = DatabaseConsultaHelper(context).readableDatabase

    override fun salvar(consulta: Consulta): Boolean {

        val conteudos = ContentValues()
        conteudos.put(DatabaseConsultaHelper.COLUNA_DESCRICAO, consulta.descricaoConsulta)
        conteudos.put(DatabaseConsultaHelper.COLUNA_DATA_CONSULTA, consulta.dataConsulta)


        try {

            escrita.insert(
                DatabaseConsultaHelper.TABELA_CONSULTAS,
                null,
                conteudos
            )

            Log.i("info_db", "Sucesso ao salvar Consulta")
        } catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao salvar Consultas")
            return false
        }

        return true
    }

    override fun atualizar(consulta: Consulta): Boolean {
        TODO("Not yet implemented")
    }

    override fun remover(idConsulta: Int): Boolean {

        val args = arrayOf(idConsulta.toString())

        try {

            escrita.delete(
                DatabaseConsultaHelper.TABELA_CONSULTAS,
                "${DatabaseConsultaHelper.COLUNA_ID_CONSULTA} = ?",
                args
            )

            Log.i("info_db", "Sucesso ao remover Consulta")
        } catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao remover Consulta")
            return false
        }

        return true
    }

    override fun listar(): List<Consulta> {

        val listaConsultas = mutableListOf<Consulta>()

        val sql = "SELECT * FROM ${DatabaseConsultaHelper.TABELA_CONSULTAS}"

        val cursor = leitura.rawQuery(sql, null)

        val indiceId = cursor.getColumnIndex(DatabaseConsultaHelper.COLUNA_ID_CONSULTA)
        val indiceDescricao = cursor.getColumnIndex(DatabaseConsultaHelper.COLUNA_DESCRICAO)
        val indiceDataConsulta = cursor.getColumnIndex(DatabaseConsultaHelper.COLUNA_DATA_CONSULTA)

        while (cursor.moveToNext()){
            val idConsulta = cursor.getInt(indiceId)
            val descricao = cursor.getString(indiceDescricao)
            val data = cursor.getString(indiceDataConsulta)

            listaConsultas.add(
                Consulta(idConsulta, descricao, data)
            )
        }

        return listaConsultas
    }

}