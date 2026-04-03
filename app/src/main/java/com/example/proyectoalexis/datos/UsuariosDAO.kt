package com.example.proyectoalexis.datos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuariosDAO{
    @Query("SELECT * FROM usuarios")
    fun getAllUsuarios(): Flow<List<Usuarios>>

    @Query("""
           SELECT usuarios.* 
            FROM usuarios INNER JOIN integrantesEquipo 
ON usuarios.idUsuario = integrantesEquipo.idUsuario
    WHERE IntegrantesEquipo.idEquipo = :idEquipo
        """)
    fun getUsuariosByIdEquipo(idEquipo: Int): Flow<List<Usuarios>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(usuario: Usuarios)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(usuario: Usuarios)

    @Delete
    suspend fun delete(usuario: Usuarios)

    @Query("SELECT COUNT(*) FROM usuarios")
    suspend fun getCount(): Int
}