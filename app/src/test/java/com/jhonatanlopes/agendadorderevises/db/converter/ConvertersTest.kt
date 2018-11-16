package com.jhonatanlopes.agendadorderevises.db.converter

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.util.*

class ConvertersTest {
    private val converters = Converters()
    private val date = Calendar.getInstance().time

    @Test
    fun deve_ConverterTimestampParaData_QuantoRecebeTimestamp() {
        val timestamp = date.time
        val result: Date? = converters.fromTimestamp(timestamp)
        assertNotNull(result)
        assertEquals(date, result)
    }

    @Test
    fun deve_ConverterDataParaTimestamp_QuandoRecebeData() {
        val timestamp = date.time
        val result = converters.dateToTimestamp(date)
        assertNotNull(result)
        assertEquals(timestamp, result)
    }
}