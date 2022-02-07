package com.example.borutoapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.borutoapp.data.local.BorutoDataBase
import com.example.borutoapp.data.repository.LocalDataSourceImpl
import com.example.borutoapp.domain.repository.LocalDataSource
import com.example.borutoapp.util.Constants.BORUTO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BorutoDataBase {

        return Room.databaseBuilder(
            context,
            BorutoDataBase::class.java,
            BORUTO_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(borutoDatabase: BorutoDataBase): LocalDataSource {

        return LocalDataSourceImpl(borutoDatabase = borutoDatabase)
    }
}