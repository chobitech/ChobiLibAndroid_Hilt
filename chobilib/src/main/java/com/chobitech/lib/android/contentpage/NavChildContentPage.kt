package com.chobitech.lib.android.contentpage

abstract class NavChildContentPage : NavContentPage() {

    open suspend fun onBeforeBackToPreviousPage() {}

}