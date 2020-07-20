package com.jhurtado.lastfm.utils

import android.os.Build
import androidx.room.Room
import com.jhurtado.lastfm.ApplicationDelegate
import com.jhurtado.lastfm.data.RetrofitFactory
import com.jhurtado.lastfm.data.RetrofitService
import com.jhurtado.lastfm.data.database.AppDatabase

/**
 * @author jhurtado
 * Date: 17/07/20
 * LasfFM test for Valid.com
 */

fun provideAppDatabase(): AppDatabase = Room.databaseBuilder(
    ApplicationDelegate.applicationContext(),
    AppDatabase::class.java,
    Build.DEVICE
).build()

fun provideRetrofitService(): RetrofitService = RetrofitFactory.getRetrofitService()

