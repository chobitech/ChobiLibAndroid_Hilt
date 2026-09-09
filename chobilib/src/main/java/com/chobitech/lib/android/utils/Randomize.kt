package com.chobitech.lib.android.utils

import com.chobitech.lib.android.ChobiLib
import kotlin.math.min


open class Randomize<T>(
    val seeds: List<T>
) {

    fun next(): T {
        val index = ChobiLib.sharedSecureRandom.nextInt(seeds.size)
        return seeds[index]
    }

    fun getRandomValues(size: Int, allowDuplication: Boolean = true): List<T> {
        if (size <= 0) {
            return listOf()
        }

        return arrayListOf<T>().also { arr ->
            when (allowDuplication) {
                true -> (0 until size).forEach { _ ->
                    arr.add(next())
                }

                false -> {
                    val maxSize = min(size, seeds.size)
                    val tmpSeeds = ArrayList(seeds)
                    (0 until maxSize).forEach { _ ->
                        val index = ChobiLib.sharedSecureRandom.nextInt(tmpSeeds.size)
                        val value = tmpSeeds.removeAt(index)
                        arr.add(value)
                    }
                }
            }
        }
    }

}