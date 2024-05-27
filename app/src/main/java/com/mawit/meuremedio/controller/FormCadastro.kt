package com.mawit.meuremedio.controller

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mawit.meuremedio.R
import com.mawit.meuremedio.database.UsuarioDAO
import com.mawit.meuremedio.databinding.ActivityFormCadastroBinding
import com.mawit.meuremedio.model.Usuario

class FormCadastro : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormCadastroBinding.inflate(layoutInflater)
    }

    private var listaUsuarios = emptyList<Usuario>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bundle = intent.extras
        var usuarioId : Int? = null
        if (bundle != null){
            usuarioId = bundle.getSerializable("usuario") as Int
        }

        val usuarioDAO = UsuarioDAO(this)

        listaUsuarios = usuarioDAO.listar()

        listaUsuarios.forEach {
            if (it.id == usuarioId){
                binding.txtNome.setText(it.nome)
                binding.txtEmail.setText(it.email)
                binding.txtSenha.setText(it.senha)
                binding.txtIdade.setText(it.idade.toString())
                binding.txtConvenio.setText(it.convenio)
                binding.txtEndereco.setText(it.endereço)
                binding.txtCuidador.setText(it.cuidador)
            }
        }


        binding.btnSalvar.setOnClickListener {

            val nome = binding.txtNome.text.toString()
            val email = binding.txtEmail.text.toString()
            val senha = binding.txtSenha.text.toString()
            val idade = binding.txtIdade.text.toString().toInt()
            val convenio = binding.txtConvenio.text.toString()
            val endereco = binding.txtEndereco.text.toString()
            val cuidador = binding.txtCuidador.text.toString()

            val usuarioAtualizar = Usuario(
                usuarioId!!, nome, email, senha, idade, convenio, endereco, cuidador
            )

            val usuarioDAO = UsuarioDAO(this)

            if (usuarioDAO.atualizar(usuarioAtualizar)){
                Toast.makeText(this,
                    "Usuário cadastrado com sucesso",
                    Toast.LENGTH_SHORT
                ).show()
            }

            finish()

        }


    }
}