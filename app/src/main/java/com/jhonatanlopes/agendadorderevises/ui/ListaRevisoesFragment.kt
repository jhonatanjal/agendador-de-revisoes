package com.jhonatanlopes.agendadorderevises.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.jhonatanlopes.agendadorderevises.R
import com.jhonatanlopes.agendadorderevises.db.entity.Revisao
import com.jhonatanlopes.agendadorderevises.viewmodel.RevisaoViewModel
import kotlinx.android.synthetic.main.lista_revisoes_fragment.*

class ListaRevisoesFragment : Fragment() {
    private lateinit var viewModel: RevisaoViewModel
    private lateinit var revisoes: List<Revisao>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(RevisaoViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.lista_revisoes_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RevisaoAdapter(context)
        configuraRecyclerView(adapter)

        viewModel.revisoes.observe(this, Observer { revisoes ->
            revisoes?.let {
                this.revisoes = it
                adapter.submitList(it)
            }
        })

        fab.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_listaRevisoesFragment_to_formularioFragment)
        }
    }

    private fun configuraRecyclerView(adapter: RevisaoAdapter) {
        lista_revisoes_recyclerview.adapter = adapter
        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(holder: RecyclerView.ViewHolder, p1: Int) {
                if (this@ListaRevisoesFragment::revisoes.isInitialized) {
                    val revisao = revisoes[holder.adapterPosition]

                    viewModel.remove(revisao)

                    val snackbar = Snackbar.make(holder.itemView, "Revisão removida", Snackbar.LENGTH_LONG)
                    snackbar.setAction("Desfazer") { viewModel.insere(revisao) }
                    snackbar.show()
                }
            }

        }).attachToRecyclerView(lista_revisoes_recyclerview)
    }
}
