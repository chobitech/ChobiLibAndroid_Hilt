package com.chobitech.lib.android.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory


inline fun <reified T : RoomDatabase> Room.createDbInstance(
    context: Context,
    fileName: String,
    password: ByteArray? = null
): T {
    System.loadLibrary("sqlcipher")

    val factory = SupportOpenHelperFactory(password)

    return Room.databaseBuilder(
        context.applicationContext,
        T::class.java,
        fileName
    )
        .openHelperFactory(factory)
        .build()
}


inline fun <reified T : RoomDatabase> Room.createDbInstance(
    context: Context,
    fileName: String,
    dbKey: DbKey
): T {
    return this.createDbInstance<T>(
        context = context,
        fileName = fileName,
        password = dbKey.getKey()
    )
}


inline fun <reified T : RoomDatabase> Room.createDbInstance(
    context: Context,
    fileName: String,
    baseKey: DbKey,
    secretKey: DbKey,
): T {
    return this.createDbInstance<T>(
        context,
        fileName,
        secretKey.computeHash(baseKey.getKey())
    )
}
