package com.easternkite.handler.basic

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Locale

class HandlerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow("")
    val uiState = _uiState.asStateFlow()

    fun dispatchEvent() {
        val sf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        _uiState.update { sf.format(System.currentTimeMillis()) }
    }
}