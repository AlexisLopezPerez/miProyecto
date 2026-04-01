package com.example.proyectoalexis.datos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface EquiposDAO {
    @Query("SELECT * FROM equipos")
    fun getAllEquipos(): Flow<List<Equipos>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(equipo: Equipos)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(equipo: Equipos)
}