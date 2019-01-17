package com.jhonatanlopes.agendadorderevisoes.utilities

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class DateExtensionKtTest {
    private val hoje: Calendar = Calendar.getInstance()

    @Test
    fun deve_DevolverDataIncrementadaEmUmDia_MetodoForChamado() {
        val amanha = hoje.time.maisUmDia()
        hoje.add(Calendar.DAY_OF_MONTH, 1)
        val esperado = hoje.time
        assertEquals(esperado.formatado(), amanha.formatado())
    }

    @Test
    fun deve_DevolverDataIncrementadaEmUmaSemana_MetodoForChamado() {
        val proximaSemana = hoje.time.maisUmaSemana()
        hoje.add(Calendar.WEEK_OF_MONTH, 1)
        val esperado = hoje.time
        assertEquals(esperado.formatado(), proximaSemana.formatado())
    }

    @Test
    fun deve_DevolverDataIncrementadaEmUmMes_MetodoForChamado() {
        val proximaMes = hoje.time.maisUmMes()
        hoje.add(Calendar.MONTH, 1)
        val esperado = hoje.time
        assertEquals(esperado.formatado(), proximaMes.formatado())
    }

    @Test
    fun deve_DevolverTrue_QuandoDataForHoje() {
        val date = hoje.time
        assertTrue(date.hoje())
    }

    @Test
    fun deve_DevolverFalse_QuandoDataNaoForHoje() {
        val date = hoje.time.maisUmDia()
        assertFalse(date.hoje())
    }
}