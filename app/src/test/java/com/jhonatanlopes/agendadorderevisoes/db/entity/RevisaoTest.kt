package com.jhonatanlopes.agendadorderevisoes.db.entity

import com.jhonatanlopes.agendadorderevisoes.utilities.formatado
import com.jhonatanlopes.agendadorderevisoes.utilities.maisUmaSemana
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import java.util.*

class RevisaoTest {

    private var revisao = Revisao("Teste", "Teste")
    private var hoje = Calendar.getInstance().time
    private var dataNoPassado = Calendar.getInstance().let { calendar ->
        calendar.set(2018, 11, 20)
        calendar.time
    }

    @Test
    fun deve_MarcarDataDaProximaRevisao_QuandoRevisaoEstaMarcadoParaHoje() {
        revisao.data = hoje
        val revisaoMarcada = revisao.marcaDataProximaRevisao()
        assertThat(revisaoMarcada, equalTo(true))
    }

    @Test
    fun naoDeve_MarcarDataDaProximaRevisao_QuandoRevisaoEstaMarcadoParaDepoisHoje() {
        val revisaoMarcada = revisao.marcaDataProximaRevisao()
        assertThat(revisaoMarcada, equalTo(false))
    }

    @Test
    fun deve_MarcarDataDaProximaRevisao_QuandoRevisaoEstaMarcadoParaAntesHoje() {
        revisao.data = dataNoPassado
        val revisaoMarcada = revisao.marcaDataProximaRevisao()
        assertThat(revisaoMarcada, equalTo(true))
    }

    @Test
    fun deve_ResetarDataDaProximaRevisao_QuandoRevisaoEstaMarcadoParaAntesHoje() {
        revisao.data = dataNoPassado
        val revisaoMarcada = revisao.marcaDataProximaRevisao()
        assertThat(revisaoMarcada, equalTo(true))

        val dataRevisao = revisao.data.formatado()
        val dataRevisaoEsperada = hoje.maisUmaSemana().formatado()
        assertThat(dataRevisao, equalTo(dataRevisaoEsperada))
    }
}