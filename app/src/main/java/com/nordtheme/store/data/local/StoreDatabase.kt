package com.nordtheme.store.data.local

import android.content.Context
import androidx.room.*

@Database(entities = [StoreItemEntity::class, RecentSearchEntity::class], version = 1, exportSchema = false)
abstract class StoreDatabase : RoomDatabase() {
    abstract fun storeDao(): StoreDao
    companion object {
        @Volatile private var INSTANCE: StoreDatabase? = null
        fun getInstance(context: Context): StoreDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(context.applicationContext, StoreDatabase::class.java, "nord_theme_store.db")
                .build().also { INSTANCE = it }
        }
    }
}
