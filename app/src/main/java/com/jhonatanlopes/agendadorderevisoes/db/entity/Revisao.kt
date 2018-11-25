package com.jhonatanlopes.agendadorderevisoes.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.jhonatanlopes.agendadorderevisoes.utilities.maisUmMes
import com.jhonatanlopes.agendadorderevisoes.utilities.maisUmaSemana
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "revisao")
data class Revisao(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    var materia: String,
    var assunto: String,
    var data: Date,
    @ColumnInfo(name = "quantidade_de_revisoes") var quantidadeDeRevisoes: Int
) : Parcelable {
    @Ignore
    constructor(
        materia: String,
        assunto: String
    ) : this(null, materia, assunto, Calendar.getInstance().time, 0)

    fun marcaDataProximaRevisao() {
        data = if (quantidadeDeRevisoes < 10) data.maisUmaSemana() else data.maisUmMes()
        quantidadeDeRevisoes++
    }
}