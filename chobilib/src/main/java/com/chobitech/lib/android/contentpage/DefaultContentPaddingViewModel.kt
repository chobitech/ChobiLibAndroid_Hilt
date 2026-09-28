package com.chobitech.lib.android.contentpage

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DefaultContentPaddingViewModel @Inject constructor(
    private val defaultContentPaddingManager: DefaultContentPaddingManager
) {

}