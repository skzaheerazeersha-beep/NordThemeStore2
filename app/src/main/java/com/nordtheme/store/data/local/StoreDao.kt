package com.nordtheme.store.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreDao {
    @Query("SELECT * FROM store_items") fun observeAll(): Flow<List<StoreItemEntity>>
    @Query("SELECT * FROM store_items WHERE id = :id LIMIT 1") fun observeById(id: String): Flow<StoreItemEntity?>
    @Query("SELECT * FROM store_items WHERE isFavorite = 1") fun observeFavorites(): Flow<List<StoreItemEntity>>
    @Query("SELECT * FROM store_items WHERE isDownloaded = 1 ORDER BY downloadedAt DESC") fun observeDownloaded(): Flow<List<StoreItemEntity>>
    @Query("SELECT * FROM store_items WHERE title LIKE '%' || :q || '%' OR category LIKE '%' || :q || '%' OR colorTag LIKE '%' || :q || '%'") fun search(q: String): Flow<List<StoreItemEntity>>
    @Query("SELECT COUNT(*) FROM store_items") suspend fun count(): Int
    @Insert(onConflict = OnConflictStrategy.IGNORE) suspend fun insertAllIgnore(items: List<StoreItemEntity>)
    @Query("UPDATE store_items SET isFavorite = :favorite WHERE id = :id") suspend fun setFavorite(id: String, favorite: Boolean)
    @Query("UPDATE store_items SET isDownloaded = 1, localFilePath = :path, downloadedAt = :ts WHERE id = :id") suspend fun markDownloaded(id: String, path: String, ts: Long)
    @Query("UPDATE store_items SET isPurchased = 1 WHERE id = :id") suspend fun markPurchased(id: String)
    @Query("SELECT * FROM recent_searches ORDER BY timestamp DESC LIMIT 10") fun observeRecentSearches(): Flow<List<RecentSearchEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun upsertSearch(entry: RecentSearchEntity)
    @Query("DELETE FROM recent_searches") suspend fun clearSearches()
}
