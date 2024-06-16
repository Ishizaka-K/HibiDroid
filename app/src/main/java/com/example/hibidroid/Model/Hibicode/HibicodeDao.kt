package com.example.hibidroid.Model.Hibicode

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HibicodeDao {

    @Query("SELECT * FROM hibicode_table ORDER BY hibicode ASC")
    fun getAll(): Flow<List<HibicodeEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(hibicode: HibicodeEntity)

    @Query("DELETE FROM hibicode_table")
    fun deleteAll()

    @Delete
    fun delete(hibicode: HibicodeEntity)

    @Update
    fun update(hibicode: HibicodeEntity)
}