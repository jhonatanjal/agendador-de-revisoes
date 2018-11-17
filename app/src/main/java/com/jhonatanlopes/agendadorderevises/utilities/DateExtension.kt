package com.jhonatanlopes.agendadorderevises.utilities

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatoBrasileiro(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "br"))
    return dateFormat.format(this)
}