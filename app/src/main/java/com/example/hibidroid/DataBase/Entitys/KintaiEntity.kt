package com.example.hibidroid.DataBase.Entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

class KintaiEntity {
    @Entity(tableName = "kintai_list")
    data class KintaiListEntity(
        @PrimaryKey
        @ColumnInfo(name = "descripsion")
        val descripsion: String
    )

}