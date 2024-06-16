package com.example.hibidroid.HibiDataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.hibidroid.Model.Hibicode.HibicodeDao
import com.example.hibidroid.Model.Hibicode.HibicodeEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [HibicodeEntity::class], version = 1)
abstract class HibiDatabase:RoomDatabase(){

    abstract fun hibicodeDao(): HibicodeDao

    companion object{
        @Volatile
        private var INSTANCE: HibiDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): HibiDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HibiDatabase::class.java,
                    "hibicode_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(HibiDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class HibiDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                scope.launch(Dispatchers.IO) {
                    populateDatabase(it.hibicodeDao())
                }
            }
        }

        private fun populateDatabase(hibicodeDao: HibicodeDao) {
            hibicodeDao.deleteAll()
            val hibicode = HibicodeEntity("Hibicode 1", "Hibicode 1 Description")
            hibicodeDao.insertAll(hibicode)
        }
    }
}