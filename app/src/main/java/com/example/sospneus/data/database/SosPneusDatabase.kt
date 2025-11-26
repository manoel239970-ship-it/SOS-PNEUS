package com.example.sospneus.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sospneus.data.dao.FavoriteContactDao
import com.example.sospneus.data.dao.TutorialStepDao
import com.example.sospneus.data.entity.FavoriteContactEntity
import com.example.sospneus.data.entity.TutorialStepEntity

/**
 * Room Database singleton para o aplicativo SOS Pneus
 */
@Database(
    entities = [TutorialStepEntity::class, FavoriteContactEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SosPneusDatabase : RoomDatabase() {
    
    abstract fun tutorialStepDao(): TutorialStepDao
    abstract fun favoriteContactDao(): FavoriteContactDao
    
    companion object {
        @Volatile
        private var INSTANCE: SosPneusDatabase? = null
        
        fun getDatabase(context: Context): SosPneusDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SosPneusDatabase::class.java,
                    "sos_pneus_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

