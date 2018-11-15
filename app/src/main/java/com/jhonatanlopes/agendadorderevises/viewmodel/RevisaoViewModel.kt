package com.jhonatanlopes.agendadorderevises.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.jhonatanlopes.agendadorderevises.db.entity.Revisao
import com.jhonatanlopes.agendadorderevises.repository.RevisaoRepository

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

}
