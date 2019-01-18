package com.jhonatanlopes.agendadorderevisoes.ui


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.*
import androidx.navigation.fragment.findNavController
import com.jhonatanlopes.agendadorderevisoes.R
import com.jhonatanlopes.agendadorderevisoes.db.entity.Revisao
import com.jhonatanlopes.agendadorderevisoes.utilities.formatado
import com.jhonatanlopes.agendadorderevisoes.viewmodel.RevisaoViewModel
import kotlinx.android.synthetic.main.detalhes_revisao.*

class DetalhesRevisaoFragment : Fragment() {
    private lateinit var viewModel: RevisaoViewModel
    private var revisao: Revisao? = null
        get() {
            return field ?: arguments?.let { bundle ->
                DetalhesRevisaoFragmentArgs.fromBundle(bundle).revisao
                    .also { field = it }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(RevisaoViewModel::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detalhes_revisao, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        revisao?.id?.let { id ->
            viewModel.busca(id).observe(this@DetalhesRevisaoFragment, Observer { revisao ->
                this.revisao = revisao
                preencheDetalhes()
            })
        }
        detalhe_revisao_btn_revisado.setOnClickListener { marcarProximaRevisao() }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_detalhes, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_editar -> editar()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun editar() {
        val action =
            DetalhesRevisaoFragmentDirections.actionDetalhesRevisaoFragmentToFormularioFragment()
        action.setRevisao(revisao)
        findNavController().navigate(action)
    }

    private fun marcarProximaRevisao() {
        revisao?.apply {
            if (marcaDataProximaRevisao()) {
                preencheDetalhes()
                viewModel.atualiza(this)
            } else {
                view?.let {
                    Snackbar.make(it, "Revisão não esta marcada para hoje", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun preencheDetalhes() {
        revisao?.run {
            detalhe_revisao_materia.text = materia
            detalhe_revisao_assunto.text = assunto
            detalhe_revisao_data.text = data.formatado()
            detalhe_revisao_numero_revisoes.text = quantidadeDeRevisoes.toString()
        }
    }

}
