package com.mawit.meuremedio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mawit.meuremedio.databinding.ItemConsultaBinding
import com.mawit.meuremedio.model.Consulta

class ConsultaAdapter(
    val onClickExcluir : (Int) -> Unit
) : RecyclerView.Adapter<ConsultaAdapter.ConsultaViewHolder>() {

    private var listaConsultas: List<Consulta> = emptyList()

    fun adicionarLista(lista: List<Consulta>){
        this.listaConsultas = lista
        notifyDataSetChanged()
    }

    inner class ConsultaViewHolder(itemBinding: ItemConsultaBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        private val binding: ItemConsultaBinding = itemBinding

        fun bind(consulta: Consulta){
            binding.txtDescricaoConsulta.text = consulta.descricaoConsulta
            binding.txtDataConsulta.text = consulta.dataConsulta


            binding.btnDeletar.setOnClickListener {
                onClickExcluir(consulta.idConsulta)
            }
        }
    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConsultaAdapter.ConsultaViewHolder {
        val itemConsultaBinding = ItemConsultaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ConsultaViewHolder(itemConsultaBinding)
    }

    override fun onBindViewHolder(holder: ConsultaAdapter.ConsultaViewHolder, position: Int) {
        val consulta = listaConsultas[position]
        holder.bind(consulta)
    }

    override fun getItemCount(): Int {
        return listaConsultas.size
    }


}