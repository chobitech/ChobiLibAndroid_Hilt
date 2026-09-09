package com.chobitech.lib.android

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource


object LocalizedStrings {

    fun getYes(context: Context) = context.getString(R.string.yes)
    val yes: String
        @Composable get() = stringResource(R.string.yes)


    fun getCancel(context: Context) = context.getString(R.string.cancel)
    val cancel: String
        @Composable get() = stringResource(R.string.cancel)


    fun getOk(context: Context) = context.getString(R.string.ok)
    val ok: String
        @Composable get() = stringResource(R.string.ok)


    fun getNo(context: Context) = context.getString(R.string.no)
    val no: String
        @Composable get() = stringResource(R.string.no)


    fun getOpen(context: Context) = context.getString(R.string.open)
    val open: String
        @Composable get() = stringResource(R.string.open)


    fun getClose(context: Context) = context.getString(R.string.close)
    val close: String
        @Composable get() = stringResource(R.string.close)


    fun getSave(context: Context) = context.getString(R.string.save)
    val save: String
        @Composable get() = stringResource(R.string.save)

}