package com.furkan.mobiversitechallenge.data.entity.search



import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.furkan.mobiversitechallenge.di.local_db.Converters
import java.io.Serializable

@Entity(tableName = "dbModel")
data class SearchListModel(

    @PrimaryKey
    @ColumnInfo
    val id: Int,

    @ColumnInfo
    @TypeConverters(Converters::class)
    val results: ArrayList<String>  = ArrayList()

)