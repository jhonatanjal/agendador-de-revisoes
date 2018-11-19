package com.jhonatanlopes.agendadorderevises.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.jhonatanlopes.agendadorderevises.utilities.maisUmDia
import java.util.*

@Entity(tableName = "revisao")
data class Revisao(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    var materia: String,
    var assunto: String,
    var data: Date,
    @ColumnInfo(name = "quantidade_de_revisoes") var quantidadeDeRevisoes: Int
) {
    @Ignore
    constructor(
        materia: String,
        assunto: String
    ) : this(null, materia, assunto, Calendar.getInstance().time.maisUmDia(), 0)
}