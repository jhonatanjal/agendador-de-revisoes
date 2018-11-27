package com.jhonatanlopes.agendadorderevisoes.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.jhonatanlopes.agendadorderevisoes.db.converter.Converters
import com.jhonatanlopes.agendadorderevisoes.db.dao.RevisaoDao
import com.jhonatanlopes.agendadorderevisoes.db.entity.Revisao
import com.jhonatanlopes.agendadorderevisoes.utilities.DATABASE_NAME

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
            ).build()
        }
    }
}