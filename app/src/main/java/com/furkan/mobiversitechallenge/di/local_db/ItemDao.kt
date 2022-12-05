package com.furkan.mobiversitechallenge.di.local_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.furkan.mobiversitechallenge.data.entity.search.SearchListModel

@Dao
interface ItemDao {

    @Query("SELECT * FROM dbModel WHERE id = :id")
    suspend fun getList(id : Int): SearchListModel

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun setList(item: SearchListModel)

    @Delete
    suspend fun deleteList(item: SearchListModel)
}