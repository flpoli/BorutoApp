package com.example.borutoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.borutoapp.data.local.dao.HeroDao
import com.example.borutoapp.data.local.dao.HeroRemoteKey
import com.example.borutoapp.data.local.dao.HeroRemoteKeyDao
import com.example.borutoapp.domain.model.Hero

@Database(
    entities = [Hero::class, HeroRemoteKey::class],
    version = 1
)
@TypeConverters(DatabaseConverter::class)
abstract class BorutoDataBase: RoomDatabase() {

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao

}