package com.jhonatanlopes.agendadorderevisoes.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import android.os.AsyncTask
import com.jhonatanlopes.agendadorderevisoes.db.RevisaoRoomDatabase
import com.jhonatanlopes.agendadorderevisoes.db.dao.RevisaoDao
import com.jhonatanlopes.agendadorderevisoes.db.entity.Revisao

class RevisaoRepository(context: Context) {
    private val revisaoDao: RevisaoDao = RevisaoRoomDatabase.getDatabase(context).revisaoDao()
    val revisoes: LiveData<List<Revisao>>

    init {
        revisoes = revisaoDao.revisoes()
    }

    fun insere(revisao: Revisao) {
        InsereAsyncTask(revisaoDao).execute(revisao)
    }

    fun atualiza(revisao: Revisao) {
        AtualizaAsyncTask(revisaoDao).execute(revisao)
    }

    fun remove(revisao: Revisao) {
        RemoveAsyncTask(revisaoDao).execute(revisao)
    }

    fun todasRevisoes(): List<Revisao> {
        return revisaoDao.todasRevisoes()
    }

    private class InsereAsyncTask(private val revisaoDao: RevisaoDao) : AsyncTask<Revisao, Unit, Unit>() {
        override fun doInBackground(vararg params: Revisao?) {
            params[0]?.let { revisaoDao.insere(it) }
        }
    }

    private class AtualizaAsyncTask(private val revisaoDao: RevisaoDao) : AsyncTask<Revisao, Unit, Unit>() {
        override fun doInBackground(vararg params: Revisao?) {
            params[0]?.let { revisaoDao.atualiza(it) }
        }
    }

    private class RemoveAsyncTask(private val dao: RevisaoDao) : AsyncTask<Revisao, Unit, Unit>() {
        override fun doInBackground(vararg params: Revisao?) {
            params[0]?.apply { dao.remove(this) }
        }
    }

}