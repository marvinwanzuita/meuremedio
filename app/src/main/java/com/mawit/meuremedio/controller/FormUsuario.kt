package com.mawit.meuremedio.controller

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mawit.meuremedio.R
import com.mawit.meuremedio.database.UsuarioDAO
import com.mawit.meuremedio.databinding.ActivityFormUsuarioBinding
import com.mawit.meuremedio.model.Usuario

class FormUsuario : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormUsuarioBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCadastrar.setOnClickListener {

            val campoEmail = binding.txtEmailUsuario.text.toString()
            val campoSenha =  binding.txtSenhaUsuario.text.toString()
            val campoConfirmarSenha =  binding.txtConfirmaSenhaUsuario.text.toString()

            val camposPreenchidos = campoEmail.isNotEmpty() &&
                    campoSenha.isNotEmpty() &&
                    campoConfirmarSenha.isNotEmpty()

            if (camposPreenchidos && campoSenha == campoConfirmarSenha){

                val usuario = Usuario(
                    -1,
                    null,
                    campoEmail,
                    campoSenha,
                    null,
                    null,
                    null,
                    null
                )

                val usuarioDAO = UsuarioDAO(this)
                if (usuarioDAO.salvar(usuario)){
                    Toast.makeText(this,
                        "Usuário cadastrado com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }


            } else {
                Toast.makeText(this,
                    "Preencha todos os campos",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }



    }


}