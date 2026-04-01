package com.example.proyectoalexis.datos

import android.content.Context
import androidx.room.Database

@Database(
    entities = [Equipos::class],
    version = 1,
    exportSchema = false
    )
abstract class AppDatabase {
    abstract fun equiposDAO(): EquiposDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase?= null

        fun getInstance(context: Context): AppDatabase{
            return INSTANCE ?:
        }
    }
}