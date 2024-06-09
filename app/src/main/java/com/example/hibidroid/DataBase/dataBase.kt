package com.example.hibidroid.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
//aaaaaa
//aaaaaa
class dataBase {
    @Database(entities = [HibicodeEntity::class], version = 1)
    abstract class AppDatabase:RoomDatabase(){
        abstract fun hibicodeDao(): HibicodeDao
        companion object {
            private var INSTANCE: AppDatabase? = null
            fun getInstance(context: Context): Any {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "hibicode_database"
                    ).build()
                    .also { INSTANCE = it }
                }
            }
        }


    }
}