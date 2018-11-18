package com.jhonatanlopes.agendadorderevises.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.jhonatanlopes.agendadorderevises.R
import com.jhonatanlopes.agendadorderevises.viewmodel.RevisaoViewModel
import kotlinx.android.synthetic.main.lista_revisoes_fragment.*

class ListaRevisoesFragment : Fragment() {
    private lateinit var viewModel: RevisaoViewModel

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
            revisoes?.let { adapter.setRevisoes(it) }
        })

        fab.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_listaRevisoesFragment_to_formularioFragment)
        }
    }

    private fun configuraRecyclerView(adapter: RevisaoAdapter) {
        lista_revisoes_recyclerview.adapter = adapter
    }
}
