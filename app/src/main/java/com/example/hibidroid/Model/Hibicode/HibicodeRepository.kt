package com.example.hibidroid.Model.Hibicode

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class HibicodeRepository(private val hibicodeDao: HibicodeDao){
    val allHibicodes: Flow<List<HibicodeEntity>> = hibicodeDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(hibicode: HibicodeEntity){
        hibicodeDao.insertAll(hibicode)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(hibicode: HibicodeEntity){
        hibicodeDao.delete(hibicode)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(hibicode: HibicodeEntity){
        hibicodeDao.update(hibicode)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll(){
        hibicodeDao.deleteAll()
    }


}