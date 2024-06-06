package com.example.hibidroid.DataBase

import kotlinx.coroutines.flow.Flow

class HibicodeRepository(private val hibicodeDao: HibicodeDao){
    val allHibicodes: Flow<List<HibicodeEntity>> = hibicodeDao.getAll()

    suspend fun insert(hibicode: HibicodeEntity){
        hibicodeDao.insertAll(hibicode)
    }

    suspend fun delete(hibicode: HibicodeEntity){
        hibicodeDao.delete(hibicode)
    }

    suspend fun update(hibicode: HibicodeEntity){
        hibicodeDao.update(hibicode)
    }
}