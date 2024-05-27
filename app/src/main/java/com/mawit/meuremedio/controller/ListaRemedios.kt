package com.mawit.meuremedio.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.mawit.meuremedio.R
import com.mawit.meuremedio.adapter.RemedioAdapter
import com.mawit.meuremedio.database.RemedioDAO
import com.mawit.meuremedio.databinding.ActivityListaRemediosBinding
import com.mawit.meuremedio.model.Remedio

class ListaRemedios : AppCompatActivity() {

    private val bindind by lazy {
        ActivityListaRemediosBinding.inflate(layoutInflater)
    }

    private var listaRemedios = emptyList<Remedio>()
    private var remedioAdapter: RemedioAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(bindind.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        bindind.btnAdicionarRemedio.setOnClickListener {
            val intent = Intent(this, FormRemedio::class.java)
            startActivity(intent)
        }

        bindind.btnListaHorarios.setOnClickListener {
            val intent = Intent(this, ListaHorarios::class.java)
            startActivity(intent)
        }

        remedioAdapter = RemedioAdapter(
            {id -> confirmarExclusao(id) },
            {remedio -> editar(remedio)}
        )


        bindind.rvListaRemedios.adapter = remedioAdapter

        bindind.rvListaRemedios.layoutManager = LinearLayoutManager(this)

    }

    private fun editar(remedio: Remedio) {

        val intent = Intent(this, FormRemedio::class.java)
        intent.putExtra("remedio", remedio)
        startActivity(intent)

    }

    private fun confirmarExclusao(id: Int) {

        val alertBuilder = AlertDialog.Builder(this)

        alertBuilder.setTitle("Confirmar exclusão")
        alertBuilder.setMessage("Deseja realmente excluir a tarefa?")

        alertBuilder.setPositiveButton("Sim"){_, _ ->

            val remedioDAO = RemedioDAO(this)
            if (remedioDAO.remover(id)){
                atualizarListaRemedios()
                Toast.makeText(this,
                    "Remédio removido com sucesso",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }

        alertBuilder.setNegativeButton("Não"){_, _ ->

        }

        alertBuilder.create().show()

    }

    private fun atualizarListaRemedios(){
        val remedioDAO = RemedioDAO(this)
        listaRemedios = remedioDAO.listar().reversed()
        remedioAdapter?.adicionarLista(listaRemedios)
    }

    override fun onStart() {
        super.onStart()
        atualizarListaRemedios()
    }

}