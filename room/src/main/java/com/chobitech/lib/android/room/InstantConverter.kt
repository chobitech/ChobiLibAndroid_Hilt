package com.chobitech.lib.android.room

import androidx.room.TypeConverter
import java.time.Instant

class InstantConverter {

    @TypeConverter
    fun fromEpochMilli(epochMs: Long?) = epochMs?.let { Instant.ofEpochMilli(it) }

    @TypeConverter
    fun fromInstant(i: Instant?) = i?.toEpochMilli()

}