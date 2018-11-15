package com.jhonatanlopes.agendadorderevises.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.jhonatanlopes.agendadorderevises.db.converter.Converters
import com.jhonatanlopes.agendadorderevises.db.dao.RevisaoDao
import com.jhonatanlopes.agendadorderevises.db.entity.Revisao
import com.jhonatanlopes.agendadorderevises.db.utilities.DATABASE_NAME

@Database(entities = [Revisao::class], version = 1)
@TypeConverters(Converters::class)
abstract class RevisaoRoomDatabase : RoomDatabase() {
    abstract fun revisaoDao(): RevisaoDao

    companion object {
        @Volatile
        private var instace: RevisaoRoomDatabase? = null

        fun getDatabase(context: Context): RevisaoRoomDatabase {
            return instace ?: synchronized(this) {
                instace ?: constroiDatabase(context).also { instace = it }
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