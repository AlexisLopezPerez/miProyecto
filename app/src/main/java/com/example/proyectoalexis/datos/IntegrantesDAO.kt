package com.example.proyectoalexis.datos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface IntegrantesDAO {
    @Query("SELECT * FROM integrantesEquipo")
    fun getAllIntegrantes(): Flow<List<IntegrantesEquipo>>

    /*@Query("SELECT * FROM usuarios WHERE idUsuario = :idUsuario")
    fun getUsuarioById(idUsuario: Int): Usuarios?*/

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(integrante: IntegrantesEquipo)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(integrante: IntegrantesEquipo)

    @Delete
    suspend fun delete(integrante: IntegrantesEquipo)

    @Query("SELECT COUNT(*) FROM integrantesEquipo")
    suspend fun getCount(): Int
}