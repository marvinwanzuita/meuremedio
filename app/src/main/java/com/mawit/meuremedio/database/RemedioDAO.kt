package com.mawit.meuremedio.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.mawit.meuremedio.model.Remedio

class RemedioDAO(context: Context) : IRemedioDAO {

    private val escrita = DatabaseRemedioHelper(context).writableDatabase
    private val leitura = DatabaseRemedioHelper(context).readableDatabase

    override fun salvar(remedio: Remedio): Boolean {

        val conteudos = ContentValues()
        conteudos.put(DatabaseRemedioHelper.COLUNA_NOME_REMEDIO, remedio.nomeRemedio)
        conteudos.put(DatabaseRemedioHelper.COLUNA_COMPONENTES, remedio.componentes)
        conteudos.put(DatabaseRemedioHelper.COLUNA_HORARIO_USO, remedio.horarioUso)
        conteudos.put(DatabaseRemedioHelper.COLUNA_PRECAUCOES, remedio.precaucoes)
        conteudos.put(DatabaseRemedioHelper.COLUNA_OBSERVACOES, remedio.observacoes)


        try {
            escrita.insert(
                DatabaseRemedioHelper.TABELA_REMEDIOS,
                null,
                conteudos
            )
            Log.i("info_db", "Sucesso ao salvar remedio")
        } catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao criar remedio")
            return false
        }
        return true
    }

    override fun atualizar(remedio: Remedio): Boolean {

        val args = arrayOf(remedio.idRemedio.toString())
        val conteudo = ContentValues()
        conteudo.put(DatabaseRemedioHelper.COLUNA_NOME_REMEDIO, remedio.nomeRemedio)
        conteudo.put(DatabaseRemedioHelper.COLUNA_COMPONENTES, remedio.componentes)
        conteudo.put(DatabaseRemedioHelper.COLUNA_HORARIO_USO, remedio.horarioUso)
        conteudo.put(DatabaseRemedioHelper.COLUNA_PRECAUCOES, remedio.precaucoes)
        conteudo.put(DatabaseRemedioHelper.COLUNA_OBSERVACOES, remedio.observacoes)

        try {

            escrita.update(
                DatabaseRemedioHelper.TABELA_REMEDIOS,
                conteudo,
                "${DatabaseRemedioHelper.COLUNA_ID_REMEDIO} = ?",
                args
            )
            Log.i("info_db", "Sucesso ao atualizar remedio")
        } catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao atualizar remedio")
            return false
        }

        return true
    }

    override fun remover(idRemedio: Int): Boolean {

        val args = arrayOf(idRemedio.toString())

        try {

            escrita.delete(
                DatabaseRemedioHelper.TABELA_REMEDIOS,
                "${DatabaseRemedioHelper.COLUNA_ID_REMEDIO} = ?",
                args
            )
            Log.i("info_db", "Sucesso ao remover remedio")
        } catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao remover remedio")
            return false
        }
        return true
    }

    override fun listar(): List<Remedio> {

        val listaTarefas = mutableListOf<Remedio>()

        val sql = "SELECT * FROM ${DatabaseRemedioHelper.TABELA_REMEDIOS}"

        val cursor = leitura.rawQuery(sql, null)

        val indiceIdRemedio = cursor.getColumnIndex(DatabaseRemedioHelper.COLUNA_ID_REMEDIO)
        val indiceNomeRemedio = cursor.getColumnIndex(DatabaseRemedioHelper.COLUNA_NOME_REMEDIO)
        val indiceCompontentes = cursor.getColumnIndex(DatabaseRemedioHelper.COLUNA_COMPONENTES)
        val indiceHorarioUso = cursor.getColumnIndex(DatabaseRemedioHelper.COLUNA_HORARIO_USO)
        val indicePrecaucoes = cursor.getColumnIndex(DatabaseRemedioHelper.COLUNA_PRECAUCOES)
        val indiceObservacoes = cursor.getColumnIndex(DatabaseRemedioHelper.COLUNA_OBSERVACOES)


        while (cursor.moveToNext()){

            val idRemedio = cursor.getInt(indiceIdRemedio)
            val nomeRemedio = cursor.getString(indiceNomeRemedio)
            val componentes = cursor.getString(indiceCompontentes)
            val horarioUso = cursor.getString(indiceHorarioUso)
            val precaucoes = cursor.getString(indicePrecaucoes)
            val observacoes = cursor.getString(indiceObservacoes)

            listaTarefas.add(
                Remedio(idRemedio, nomeRemedio, componentes, horarioUso, precaucoes, observacoes)
            )

        }

        return listaTarefas

    }


}