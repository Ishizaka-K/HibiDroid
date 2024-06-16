package com.example.hibidroid.Model.Hibicode

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="hibicode_table")
data class HibicodeEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "hibicode")
        val word: String,
        val code: String
)