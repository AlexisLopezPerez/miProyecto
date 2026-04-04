package com.example.proyectoalexis.datos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TareasDAO {
    @Query("SELECT * FROM tareas")
    fun getAllTareas(): Flow<List<Tareas>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(tareas: Tareas)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tareas: Tareas)

    @Delete
    suspend fun delete(tareas: Tareas)

    @Query("SELECT COUNT(*) FROM tareas")
    suspend fun getCount(): Int
}