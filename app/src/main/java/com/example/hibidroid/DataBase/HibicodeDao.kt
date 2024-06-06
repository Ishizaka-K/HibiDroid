package com.example.hibidroid.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HibicodeDao {

    @Query("SELECT * FROM hibicode_table")
    fun getAll(): Flow<List<HibicodeEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(hibicode: HibicodeEntity)

    @Delete
    fun delete(hibicode: HibicodeEntity)

    @Update
    fun update(hibicode: HibicodeEntity)
}