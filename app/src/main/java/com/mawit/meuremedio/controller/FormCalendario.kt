package com.mawit.meuremedio.controller

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.mawit.meuremedio.R
import com.mawit.meuremedio.adapter.ConsultaAdapter
import com.mawit.meuremedio.database.ConsultaDAO
import com.mawit.meuremedio.databinding.ActivityFormCalendarioBinding
import com.mawit.meuremedio.model.Consulta

class FormCalendario : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormCalendarioBinding.inflate(layoutInflater)
    }

    private var listaConsultas = emptyList<Consulta>()
    private var consultaAdapter: ConsultaAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.cvCalendarioDataConsulta.setOnDateChangeListener { _, year, month, day ->
            val date = ("%02d".format(day) + "-" + "%02d".format((month+1)) + "-" + year)
            binding.txtDataConsulta.setText(date)
        }

        binding.btnSalvarConsulta.setOnClickListener {

            val descricao = binding.txtDescricaoConsulta.text.toString()
            val data = binding.txtDataConsulta.text.toString()

            val camposPreenchidos = descricao.isNotEmpty() && data.isNotEmpty()

            if (camposPreenchidos){

                val consulta = Consulta(
                    -1,
                    descricao,
                    data
                )

                val consultaDAO = ConsultaDAO(this)
                if (consultaDAO.salvar(consulta)){
                    Toast.makeText(this,
                        "Consulta cadastrada com sucesso.",
                        Toast.LENGTH_SHORT
                    ).show()
                }


            } else {
                Toast.makeText(this,
                    "Preencha todos os campos",
                    Toast.LENGTH_SHORT
                ).show()
            }

            atualizarListaConsultas()
        }


        consultaAdapter = ConsultaAdapter { id ->
            confirmarExclusao(id)
        }
        consultaAdapter?.adicionarLista(listaConsultas)
        binding.rvListaConsultas.adapter = consultaAdapter
        binding.rvListaConsultas.layoutManager = LinearLayoutManager(this)

    }

    private fun confirmarExclusao(id: Int) {

        val alertBuilder = AlertDialog.Builder(this)

        alertBuilder.setTitle("Confirmar exclusão")
        alertBuilder.setMessage("Deseja realmente excluir a consulta?")

        alertBuilder.setPositiveButton("Sim"){_, _, ->

            val consultaDAO = ConsultaDAO(this)
            if (consultaDAO.remover(id)){
                atualizarListaConsultas()
                Toast.makeText(this,
                    "Consulta removido com sucesso!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this,
                    "Erro ao remover consulta!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        alertBuilder.setNegativeButton("Não"){_, _, -> }

        alertBuilder.create().show()
    }

    private fun atualizarListaConsultas(){
        val consultaDAO = ConsultaDAO(this)
        listaConsultas = consultaDAO.listar().reversed()
        consultaAdapter?.adicionarLista(listaConsultas)
    }

    override fun onStart() {
        super.onStart()
        atualizarListaConsultas()
    }
}