package com.jhonatanlopes.agendadorderevisoes.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.jhonatanlopes.agendadorderevisoes.db.entity.Revisao
import com.jhonatanlopes.agendadorderevisoes.repository.RevisaoRepository

class RevisaoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: RevisaoRepository = RevisaoRepository(application)
    val revisoes: LiveData<List<Revisao>>

    init {
        revisoes = repository.revisoes
    }

    fun insere(revisao: Revisao) {
        repository.insere(revisao)
    }

    fun atualiza(revisao: Revisao) {
        repository.atualiza(revisao)
    }

    fun busca(id: Int): LiveData<Revisao> {
        return repository.busca(id)
    }

    fun remove(revisao: Revisao) {
        repository.remove(revisao)
    }

}
