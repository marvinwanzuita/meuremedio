package com.mawit.meuremedio.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mawit.meuremedio.R
import com.mawit.meuremedio.database.UsuarioDAO
import com.mawit.meuremedio.databinding.ActivityFormLoginBinding
import com.mawit.meuremedio.model.Usuario

class FormLogin : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormLoginBinding.inflate(layoutInflater)
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

        binding.btnEntrar.setOnClickListener {

            val campoEmail = binding.txtEmailLogin.text.toString()
            val campoSenha =  binding.txtSenhaLogin.text.toString()

            val usuarioDAO = UsuarioDAO(this)

            listaUsuarios = usuarioDAO.listar()

            var usuarioId : Int? = null


            listaUsuarios.forEach {
                if (it.email == campoEmail){
                    if (it.senha == campoSenha){
                        usuarioId = it.id
                    }
                }
            }

            if (usuarioId != null){

                val intent = Intent(this, MenuPrincipal::class.java)
                intent.putExtra("usuario", usuarioId)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this,
                    "Usuário e/ou senha incorretos.",
                    Toast.LENGTH_LONG)
                    .show()
            }


        }

    }
}