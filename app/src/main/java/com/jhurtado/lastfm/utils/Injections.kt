package com.jhurtado.lastfm.utils

import android.app.Activity
import android.os.Build
import androidx.room.Room
import com.jhurtado.lastfm.ApplicationDelegate
import com.jhurtado.lastfm.UseCaseHandler
import com.jhurtado.lastfm.data.database.AppDatabase
import com.jhurtado.lastfm.data.source.DataRepository
import com.jhurtado.lastfm.data.source.DataSource
import com.jhurtado.lastfm.data.source.local.LocalDataSource
import com.jhurtado.lastfm.data.source.remote.RemoteDataSource

/**
 * @author jhurtado
 * Date: 17/07/20
 * LasfFM test for Valid.com
 */
fun provideDataRepository(activity: Activity): DataRepository {
    return DataRepository.getInstance(provideLocalDataSource(), provideRemoteDataSource(), activity)
}

fun provideLocalDataSource(): DataSource = LocalDataSource.getInstance()

fun provideRemoteDataSource(): DataSource = RemoteDataSource.getInstance()

fun provideUseCaseHandler() = UseCaseHandler.getInstance()

fun provideAppDatabase(): AppDatabase = Room.databaseBuilder(
    ApplicationDelegate.applicationContext(),
    AppDatabase::class.java,
    Build.DEVICE
).build()
