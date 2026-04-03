package com.example.proyectoalexis.datos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Equipos::class, Usuarios::class, IntegrantesEquipo::class],
    version = 3,
    exportSchema = false
    )
abstract class AppDatabase: RoomDatabase() {
    abstract fun equiposDAO(): EquiposDAO

    abstract fun usuariosDAO(): UsuariosDAO

    abstract fun integrantesDAO(): IntegrantesDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tareas_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}