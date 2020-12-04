package com.devkanhaiya.itunessearchprojectkotlin.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(OffData::class), version = 1, exportSchema = false)

abstract class OffDataBase : RoomDatabase(){

        abstract fun getNoteDao() : OffDao

        companion object {

            @Volatile
            private var INSTANCE: OffDataBase? = null

            fun getDatabase(context: Context): OffDataBase {
                // if the INSTANCE is not null, then return it,
                // if it is, then create the database
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        OffDataBase::class.java,
                        "note_table"
                    ).build()
                    INSTANCE = instance
                    // return instance
                    instance
                }
            }
        }

}