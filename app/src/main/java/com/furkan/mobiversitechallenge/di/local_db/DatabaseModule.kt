package com.furkan.mobiversitechallenge.di.local_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.furkan.mobiversitechallenge.data.entity.search.SearchListModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module



val roomDBModule = module {
    single { provideRoomDb(androidContext()) }
    single { provideItemDao(get()) }
}

fun provideRoomDb(context: Context): RoomDB {
    return Room
        .databaseBuilder(context, RoomDB::class.java, "LocalDb")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
}

fun provideItemDao(roomDB: RoomDB): ItemDao {
    return roomDB.itemDao()
}

@Database(entities = [SearchListModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class RoomDB : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}