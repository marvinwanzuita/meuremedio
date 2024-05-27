package com.mawit.meuremedio.controller

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mawit.meuremedio.R
import com.mawit.meuremedio.databinding.ActivityMenuAutenticacaoBinding

class MenuAutenticacao : AppCompatActivity() {

    private val bindind by lazy {
        ActivityMenuAutenticacaoBinding.inflate(layoutInflater)
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

        bindind.btnAutenticacao.setOnClickListener {
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
        }

        bindind.btnNovoCadastro.setOnClickListener {
            val intent = Intent(this, FormUsuario::class.java)
            startActivity(intent)
        }

        bindind.btnSuporte.setOnClickListener {
            val intent = Intent(this, Suporte::class.java)
            startActivity(intent)
        }


    }
}