package com.mawit.meuremedio.controller

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mawit.meuremedio.R
import com.mawit.meuremedio.database.RemedioDAO
import com.mawit.meuremedio.databinding.ActivityFormRemedioBinding
import com.mawit.meuremedio.model.Remedio

class FormRemedio : AppCompatActivity() {

    private val bindind by lazy {
        ActivityFormRemedioBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(bindind.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recuperar tarefa passada

        var remedio: Remedio? = null
        val bundle = intent.extras

        if (bundle != null){

            remedio = bundle.getSerializable("remedio") as Remedio
            bindind.txtNomeRemedio.setText(remedio.nomeRemedio)
            bindind.txtComponentes.setText(remedio.componentes)
            bindind.txtHorarioUso.setText(remedio.horarioUso)
            bindind.txtPrecaucoes.setText(remedio.precaucoes)
            bindind.txtObservacoes.setText(remedio.observacoes)
        }



        bindind.btnCadastrarRemedio.setOnClickListener {

            val nomeRemedio = bindind.txtNomeRemedio.text.toString()
            val componentes = bindind.txtComponentes.text.toString()
            val horarioUso = bindind.txtHorarioUso.text.toString()
            val precaucoes = bindind.txtPrecaucoes.text.toString()
            val observacoes = bindind.txtObservacoes.text.toString()

            val camposPreenchidos = nomeRemedio.isNotEmpty() &&
                                    componentes.isNotEmpty() &&
                                    horarioUso.isNotEmpty() &&
                                    precaucoes.isNotEmpty() &&
                                    observacoes.isNotEmpty()

            if (camposPreenchidos){

                if (remedio != null){
                   editar(remedio)
                } else {
                    salvar(nomeRemedio, componentes, horarioUso, precaucoes, observacoes)
                }

            } else {
                Toast.makeText(this,
                    "Preencha todos os campos",
                    Toast.LENGTH_SHORT)
                .show()
            }

        }

    }

    private fun editar(remedio: Remedio) {

        val nomeRemedio = bindind.txtNomeRemedio.text.toString()
        val componentes = bindind.txtComponentes.text.toString()
        val horarioUso = bindind.txtHorarioUso.text.toString()
        val precaucoes = bindind.txtPrecaucoes.text.toString()
        val observacoes = bindind.txtObservacoes.text.toString()

        val remedioAtualizar = Remedio(
            remedio.idRemedio,
            nomeRemedio,
            componentes,
            horarioUso,
            precaucoes,
            observacoes
        )

        val remedioDAO = RemedioDAO(this)
        if (remedioDAO.atualizar(remedioAtualizar)) {
            Toast.makeText(
                this,
                "Remédio atualizado com sucesso",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }


    }

    private fun salvar(
        nomeRemedio: String,
        componentes: String,
        horarioUso: String,
        precaucoes: String,
        observacoes: String
    ) {
        val remedio = Remedio(
            -1,
            nomeRemedio,
            componentes,
            horarioUso,
            precaucoes,
            observacoes
        )

        val remedioDAO = RemedioDAO(this)
        if (remedioDAO.salvar(remedio)) {
            Toast.makeText(
                this,
                "Remédio cadastrado com sucesso",
                Toast.LENGTH_SHORT
            )
                .show()
            finish()
        }
    }
}