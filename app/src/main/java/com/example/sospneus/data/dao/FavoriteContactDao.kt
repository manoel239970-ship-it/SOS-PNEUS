package com.example.sospneus.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.sospneus.data.entity.FavoriteContactEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO para operações com contatos favoritos
 */
@Dao
interface FavoriteContactDao {
    
    @Query("SELECT * FROM favorite_contacts ORDER BY name ASC")
    fun getAllContacts(): Flow<List<FavoriteContactEntity>>
    
    @Query("SELECT * FROM favorite_contacts WHERE id = :id")
    suspend fun getContactById(id: Long): FavoriteContactEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: FavoriteContactEntity): Long
    
    @Update
    suspend fun updateContact(contact: FavoriteContactEntity)
    
    @Delete
    suspend fun deleteContact(contact: FavoriteContactEntity)
    
    @Query("DELETE FROM favorite_contacts WHERE id = :id")
    suspend fun deleteContactById(id: Long)
}

