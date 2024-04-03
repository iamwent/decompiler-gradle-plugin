package io.github.iamwent.decompiler.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val nameState = MutableStateFlow("Hello")

    fun greet() {
        viewModelScope.launch {
            delay(1000)
            nameState.value = "Hi"
        }
    }
}
