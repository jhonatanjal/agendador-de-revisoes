package com.jhonatanlopes.agendadorderevisoes.utilities

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*

class DateExtensionKtTest {
    private val hoje: Calendar = Calendar.getInstance()

    @Test
    fun deve_DevolverDataIncrementadaEmUmDia_QuandoRecebeData() {
        val amanha = hoje.time.maisUmDia()
        hoje.add(Calendar.DAY_OF_MONTH, 1)
        val esperado = hoje.time
        assertEquals(esperado.formatado(), amanha.formatado())
    }

    @Test
    fun deve_DevolverDataIncrementadaEmUmaSemana_QuandoRecebeData() {
        val proximaSemana = hoje.time.maisUmaSemana()
        hoje.add(Calendar.WEEK_OF_MONTH, 1)
        val esperado = hoje.time
        assertEquals(esperado.formatado(), proximaSemana.formatado())
    }

    @Test
    fun deve_DevolverDataIncrementadaEmUmMes_QuandoRecebeData() {
        val proximaMes = hoje.time.maisUmMes()
        hoje.add(Calendar.MONTH, 1)
        val esperado = hoje.time
        assertEquals(esperado.formatado(), proximaMes.formatado())
    }

    @Test
    fun deve_DevolverSeDataEhHoje() {
        val date = hoje.time
        assertTrue(date.hoje())
    }
}