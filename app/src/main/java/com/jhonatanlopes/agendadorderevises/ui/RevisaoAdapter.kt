package com.jhonatanlopes.agendadorderevises.ui

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jhonatanlopes.agendadorderevises.R
import com.jhonatanlopes.agendadorderevises.db.entity.Revisao
import com.jhonatanlopes.agendadorderevises.utilities.formatado
import kotlinx.android.synthetic.main.item_revisao.view.*

class RevisaoAdapter(
    context: Context?
) : ListAdapter<Revisao, RevisaoAdapter.RevisaoViewHolder>(MyDiffcallback()) {

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RevisaoViewHolder {
        val itemView = inflater.inflate(R.layout.item_revisao, parent, false)
        return RevisaoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RevisaoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RevisaoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val materia = itemView.item_revisao_materia
        private val assunto = itemView.item_revisao_assunto
        private val data = itemView.item_revisao_data

        fun bind(revisao: Revisao) {
            materia.text = revisao.materia
            assunto.text = revisao.assunto
            data.text = revisao.data.formatado()
        }
    }

    class MyDiffcallback : DiffUtil.ItemCallback<Revisao>() {
        override fun areItemsTheSame(revisaoAntiga: Revisao, novaRevisao: Revisao) =
            revisaoAntiga.id == novaRevisao.id

        override fun areContentsTheSame(revisaoAntiga: Revisao, novaRevisao: Revisao) =
            revisaoAntiga == novaRevisao
    }

}