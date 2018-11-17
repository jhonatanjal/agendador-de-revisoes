package com.jhonatanlopes.agendadorderevises.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import android.os.AsyncTask
import com.jhonatanlopes.agendadorderevises.db.converter.Converters
import com.jhonatanlopes.agendadorderevises.db.dao.RevisaoDao
import com.jhonatanlopes.agendadorderevises.db.entity.Revisao
import com.jhonatanlopes.agendadorderevises.utilities.DATABASE_NAME

@Database(entities = [Revisao::class], version = 1)
@TypeConverters(Converters::class)
abstract class RevisaoRoomDatabase : RoomDatabase() {
    abstract fun revisaoDao(): RevisaoDao

    companion object {
        @Volatile
        private var instance: RevisaoRoomDatabase? = null

        fun getDatabase(context: Context): RevisaoRoomDatabase {
            return instance ?: synchronized(this) {
                instance ?: constroiDatabase(context).also { instance = it }
            }
        }

        private fun constroiDatabase(context: Context): RevisaoRoomDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                RevisaoRoomDatabase::class.java, DATABASE_NAME
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onOpen(db: SupportSQLiteDatabase) {
                        PopulaAsyncTask().execute()
                    }
                })
                .build()
        }

        private class PopulaAsyncTask() : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit) {
                val dao = instance?.revisaoDao()
                dao?.apagaTodas()
                dao?.insere(Revisao("Direito", "Direito"))
                dao?.insere(Revisao("Matematica", "Matematica"))
            }
        }
    }
}