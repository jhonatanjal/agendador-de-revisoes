package com.jhonatanlopes.agendadorderevisoes.utilities

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatado(): String {
    val dateFormat = SimpleDateFormat.getDateInstance()
    return dateFormat.format(this)
}

fun Date.maisUmDia(): Date {
    val calendar = incremenataDataEmUm(Calendar.DAY_OF_MONTH)
    return calendar.time
}

fun Date.maisUmaSemana(): Date {
    val calendar = incremenataDataEmUm(Calendar.WEEK_OF_MONTH)
    return calendar.time
}

fun Date.maisUmMes(): Date {
    val calendar = incremenataDataEmUm(Calendar.MONTH)
    return calendar.time
}

fun Date.hoje(): Boolean {
    val hoje = Calendar.getInstance().time
    return hoje.formatado() == this.formatado()
}

fun Date.antesDeHoje(): Boolean {
    val hoje = Calendar.getInstance()
    return this.before(hoje.time)
}

private fun Date.incremenataDataEmUm(unidadeDeTempo: Int): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(unidadeDeTempo, 1)
    return calendar
}