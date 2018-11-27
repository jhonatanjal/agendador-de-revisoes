package com.jhonatanlopes.agendadorderevisoes.db.dao

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.jhonatanlopes.agendadorderevisoes.db.RevisaoRoomDatabase
import com.jhonatanlopes.agendadorderevisoes.db.entity.Revisao
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RevisaoDaoTest {
    private lateinit var db: RevisaoRoomDatabase
    private lateinit var dao: RevisaoDao

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getContext()
        db = Room.inMemoryDatabaseBuilder(context, RevisaoRoomDatabase::class.java).build()
        dao = db.revisaoDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun deve_InserirRevisaoNoBanco_QuandoRecebeRevisao() {
        val revisao = Revisao("Teste", "Teste")

        dao.insere(revisao)
        val revisoes = dao.todasRevisoes()
        assertNotNull(revisoes)
    }
}