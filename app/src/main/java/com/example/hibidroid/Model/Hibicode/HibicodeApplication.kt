package com.example.hibidroid.Model.Hibicode

import android.app.Application
import com.example.hibidroid.HibiDataBase.HibiDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class HibicodeApplication:Application(){
    val application = CoroutineScope(SupervisorJob())
    val database by lazy { HibiDatabase.getDatabase(this, application) }
    val repository by lazy { HibicodeRepository(database.hibicodeDao()) }
}