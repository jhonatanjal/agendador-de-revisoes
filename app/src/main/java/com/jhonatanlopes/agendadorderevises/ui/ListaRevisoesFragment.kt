package com.jhonatanlopes.agendadorderevises.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jhonatanlopes.agendadorderevises.R
import com.jhonatanlopes.agendadorderevises.viewmodel.RevisaoViewModel

class ListaRevisoesFragment : Fragment() {

    companion object {
        fun newInstance() = ListaRevisoesFragment()
    }

    private lateinit var viewModel: RevisaoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.lista_revisoes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RevisaoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
