package com.jhonatanlopes.agendadorderevises.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jhonatanlopes.agendadorderevises.R
import com.jhonatanlopes.agendadorderevises.db.entity.Revisao
import com.jhonatanlopes.agendadorderevises.utilities.formatoBrasileiro
import kotlinx.android.synthetic.main.item_revisao.view.*

class RevisaoAdapter(
    context: Context?
) : RecyclerView.Adapter<RevisaoAdapter.RevisaoViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private lateinit var revisoes: List<Revisao>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RevisaoViewHolder {
        val itemView = inflater.inflate(R.layout.item_revisao, parent, false)
        return RevisaoViewHolder(itemView)
    }

    override fun getItemCount(): Int = if (this::revisoes.isInitialized) revisoes.size else 0

    override fun onBindViewHolder(holder: RevisaoViewHolder, position: Int) {
        if (this::revisoes.isInitialized)
            holder.bind(revisoes[position])
    }

    fun setRevisoes(revisoes: List<Revisao>) {
        this.revisoes = revisoes
        notifyDataSetChanged()
    }

    class RevisaoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val materia = itemView.item_revisao_materia
        private val assunto = itemView.item_revisao_assunto
        private val data = itemView.item_revisao_data

        fun bind(revisao: Revisao) {
            materia.text = revisao.materia
            assunto.text = revisao.assunto
            data.text = revisao.data.formatoBrasileiro()
        }
    }

}