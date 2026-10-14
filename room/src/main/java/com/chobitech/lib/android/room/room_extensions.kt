package com.chobitech.lib.android.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory


inline fun <reified T : RoomDatabase> Room.createDbInstance(
    context: Context,
    fileName: String,
    callback: RoomDatabase.Callback? = null,
    migrations: List<Migration> = listOf(),
    password: ByteArray? = null
): T {
    System.loadLibrary("sqlcipher")

    val factory = SupportOpenHelperFactory(password)

    return Room.databaseBuilder(
        context.applicationContext,
        T::class.java,
        fileName
    ).apply {
        openHelperFactory(factory)

        callback?.also { addCallback(it) }

        if (migrations.isNotEmpty()) {
            addMigrations(*migrations.toTypedArray())
        }
    }
        .build()
}


inline fun <reified T : RoomDatabase> Room.createDbInstance(
    context: Context,
    fileName: String,
    dbKey: DbKey,
    callback: RoomDatabase.Callback? = null,
    migrations: List<Migration> = listOf(),
): T {
    return this.createDbInstance<T>(
        context = context,
        fileName = fileName,
        callback = callback,
        migrations = migrations,
        password = dbKey.getKey()
    )
}


inline fun <reified T : RoomDatabase> Room.createDbInstance(
    context: Context,
    fileName: String,
    baseKey: DbKey,
    secretKey: DbKey,
    callback: RoomDatabase.Callback? = null,
    migrations: List<Migration> = listOf(),
): T {
    return this.createDbInstance<T>(
        context =  context,
        fileName = fileName,
        callback = callback,
        migrations = migrations,
        password = secretKey.computeHash(baseKey.getKey())
    )
}
