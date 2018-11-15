package com.jhonatanlopes.agendadorderevises.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.jhonatanlopes.agendadorderevises.db.entity.Revisao

@Dao
interface RevisaoDao {

    @Insert
    fun insere(revisao: Revisao)

    @Update
    fun atualiza(revisao: Revisao)

    @Query("SELECT * FROM revisao ORDER BY data")
    fun todasRevisoes(): LiveData<List<Revisao>>
}