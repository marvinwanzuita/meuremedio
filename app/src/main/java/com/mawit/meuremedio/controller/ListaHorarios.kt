package com.mawit.meuremedio.controller

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.mawit.meuremedio.R
import com.mawit.meuremedio.adapter.HorarioAdapter
import com.mawit.meuremedio.database.RemedioDAO
import com.mawit.meuremedio.databinding.ActivityListaHorariosBinding
import com.mawit.meuremedio.model.Remedio

class ListaHorarios : AppCompatActivity() {

    private val bindind by lazy {
        ActivityListaHorariosBinding.inflate(layoutInflater)
    }

    private var listaRemedios = emptyList<Remedio>()
    private var horarioAdapter: HorarioAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(bindind.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        horarioAdapter = HorarioAdapter()

        bindind.rvListaHorarios.adapter = horarioAdapter

        bindind.rvListaHorarios.layoutManager = LinearLayoutManager(this)

    }

    private fun atualizarListaHorarios(){
        val remedioDAO = RemedioDAO(this)
        listaRemedios = remedioDAO.listar()
        horarioAdapter?.adicionarLista(listaRemedios)
    }


    override fun onStart() {
        super.onStart()
        atualizarListaHorarios()
    }

}