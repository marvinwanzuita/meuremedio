package com.mawit.meuremedio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mawit.meuremedio.databinding.ItemHorarioBinding
import com.mawit.meuremedio.model.Remedio

class HorarioAdapter() : RecyclerView.Adapter<HorarioAdapter.HorarioViewHolder>() {

    private var listaRemedios: List<Remedio> = emptyList()

    fun adicionarLista(lista: List<Remedio>){
        this.listaRemedios = lista
        notifyDataSetChanged()
    }

    inner class HorarioViewHolder(itemBinding: ItemHorarioBinding)
        : RecyclerView.ViewHolder(itemBinding.root){

        private val binding: ItemHorarioBinding = itemBinding

        fun bind(remedio: Remedio){
            binding.txtNomeRemedio.text = remedio.nomeRemedio
            binding.txtHorarioRemedio.text = remedio.horarioUso
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorarioViewHolder {

        val itemHorarioBinding = ItemHorarioBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return HorarioViewHolder(itemHorarioBinding)

    }

    override fun getItemCount(): Int {
        return listaRemedios.size
    }

    override fun onBindViewHolder(holder: HorarioViewHolder, position: Int) {
        val remedio = listaRemedios[position]
        holder.bind(remedio)
    }


}