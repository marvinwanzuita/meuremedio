package com.mawit.meuremedio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mawit.meuremedio.databinding.ItemListaBinding
import com.mawit.meuremedio.model.Remedio

class RemedioAdapter(
    val onClickExcluir: (Int) -> Unit,
    val onClickEditar: (Remedio) -> Unit
) : RecyclerView.Adapter<RemedioAdapter.RemedioViewHolder>() {

    private var listaRemedios: List<Remedio> = emptyList()

    fun adicionarLista(lista: List<Remedio>){
        this.listaRemedios = lista
        notifyDataSetChanged()
    }

    inner class RemedioViewHolder(itemBinding: ItemListaBinding)
        : RecyclerView.ViewHolder(itemBinding.root){

        private val binding: ItemListaBinding = itemBinding

        fun bind(remedio: Remedio){
            binding.txtNomeRemedio.text = remedio.nomeRemedio
            binding.txtComponente.text = remedio.componentes
            binding.txtHorarioUso.text = remedio.horarioUso
            binding.txtPrecaucoes.text = remedio.precaucoes
            binding.txtObservacoes.text = remedio.observacoes

            binding.btnDeletar.setOnClickListener {
                onClickExcluir(remedio.idRemedio)
            }

            binding.btnEditar.setOnClickListener {
                onClickEditar(remedio)
            }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RemedioAdapter.RemedioViewHolder {
        val itemRemedioBinding = ItemListaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return RemedioViewHolder(itemRemedioBinding)
    }

    override fun onBindViewHolder(holder: RemedioAdapter.RemedioViewHolder, position: Int) {
        val remedio = listaRemedios[position]
        holder.bind(remedio)
    }

    override fun getItemCount(): Int {
        return listaRemedios.size
    }

}