package com.jhonatanlopes.agendadorderevises.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.*
import androidx.navigation.fragment.findNavController
import com.jhonatanlopes.agendadorderevises.R
import com.jhonatanlopes.agendadorderevises.db.entity.Revisao
import com.jhonatanlopes.agendadorderevises.viewmodel.RevisaoViewModel
import kotlinx.android.synthetic.main.fragment_formulario.*

class FormularioFragment : Fragment() {
    lateinit var viewModel: RevisaoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(RevisaoViewModel::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_formulario, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_formulario, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.menu_confirmar -> {
            criaRevisao()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    private fun criaRevisao() {
        val materia = formulario_campo_materia.text.toString()
        val assunto = formulario_campo_assunto.text.toString()

        if (materia.isNotBlank() && assunto.isNotBlank()) {
            val revisao = Revisao(materia, assunto)
            viewModel.insere(revisao)
            findNavController().navigateUp()
        } else {
            view?.let {
                Snackbar.make(it, "Digite a materia e assunto da revisão", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
