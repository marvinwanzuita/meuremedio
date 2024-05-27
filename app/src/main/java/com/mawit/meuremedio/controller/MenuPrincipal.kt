package com.mawit.meuremedio.controller

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mawit.meuremedio.R
import com.mawit.meuremedio.databinding.ActivityMenuPrincipalBinding

class MenuPrincipal : AppCompatActivity() {

    private val bindind by lazy {
        ActivityMenuPrincipalBinding.inflate(layoutInflater)
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

        val bundle = intent.extras
        val usuario = bundle?.getSerializable("usuario") as Int

        bindind.btnPerfil.setOnClickListener {
            val intent = Intent(this, FormCadastro::class.java)
            intent.putExtra("usuario", usuario)
            startActivity(intent)
        }


        bindind.btnListaRemedios.setOnClickListener {
            val intent = Intent(this, ListaRemedios::class.java)
            startActivity(intent)
        }

        bindind.btnCalendario.setOnClickListener {
            val intent = Intent(this, FormCalendario::class.java)
            startActivity(intent)
        }


        bindind.btnSair.setOnClickListener {
            val intent = Intent(this, MenuAutenticacao::class.java)
            startActivity(intent)
            finish()
        }



    }
}