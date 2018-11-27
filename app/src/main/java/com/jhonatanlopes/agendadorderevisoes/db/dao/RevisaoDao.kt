package com.jhonatanlopes.agendadorderevisoes.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.jhonatanlopes.agendadorderevisoes.db.entity.Revisao

@Dao
interface RevisaoDao {

    @Insert
    fun insere(revisao: Revisao)

    @Update
    fun atualiza(revisao: Revisao)

    @Query("SELECT * FROM revisao ORDER BY data")
    fun revisoes(): LiveData<List<Revisao>>

    @Query("SELECT * FROM revisao")
    fun todasRevisoes(): List<Revisao>

    @Delete
    fun remove(revisao: Revisao)

    @Query("DELETE FROM revisao")
    fun apagaTodas()
}