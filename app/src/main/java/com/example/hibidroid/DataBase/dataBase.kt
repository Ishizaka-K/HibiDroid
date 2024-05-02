package com.example.hibidroid.DataBase

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase
import com.example.hibidroid.DataBase.Entitys.KintaiEntity

class dataBase {
    @Database(entities = [KintaiEntity::class], version = 1)
    abstract class AppDatabase:RoomDatabase(){

    }
}