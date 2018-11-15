package com.jhonatanlopes.agendadorderevises.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "revisao")
class Revisao(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    var materia: String,
    var assunto: String,
    var data: Date,
    @ColumnInfo(name = "quantidade_de_revisoes") var quantidadeDeRevisoes: Int
) {
    constructor(
        materia: String,
        assunto: String
    ) : this(null, materia, assunto, Calendar.getInstance().time, 0)
}