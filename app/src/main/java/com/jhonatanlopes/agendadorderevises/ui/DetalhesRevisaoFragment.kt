package com.jhonatanlopes.agendadorderevises.ui


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jhonatanlopes.agendadorderevises.R
import com.jhonatanlopes.agendadorderevises.db.entity.Revisao
import com.jhonatanlopes.agendadorderevises.utilities.formatado
import com.jhonatanlopes.agendadorderevises.utilities.hoje
import com.jhonatanlopes.agendadorderevises.viewmodel.RevisaoViewModel
import kotlinx.android.synthetic.main.detalhes_revisao.*

class DetalhesRevisaoFragment : Fragment() {
    lateinit var viewModel: RevisaoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
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
        val revisao = arguments?.let {
            val args = DetalhesRevisaoFragmentArgs.fromBundle(it)
            args.revisao
        }
        preencheDetalhes(revisao)
        detalhe_revisao_btn_revisado.setOnClickListener {
            revisao?.run { marcarProximaRevisao(this) }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun marcarProximaRevisao(revisao: Revisao) {
        revisao.apply {
            if (data.hoje()) {
                marcaDataProximaRevisao()
                preencheDetalhes(this)
                viewModel.atualiza(this)
            } else {
                view?.let {
                    Snackbar.make(it, "Revisão não esta marcada para hoje", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun preencheDetalhes(revisao: Revisao?) {
        revisao?.run {
            detalhe_revisao_materia.text = materia
            detalhe_revisao_assunto.text = assunto
            detalhe_revisao_data.text = data.formatado()
            detalhe_revisao_numero_revisoes.text = quantidadeDeRevisoes.toString()
        }
    }

}
