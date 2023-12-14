package com.d121211040.photography.ui.activities.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211040.photography.MyApplication
import com.d121211040.photography.data.models.Photography
import com.d121211040.photography.data.repository.PhotographyRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainUiState {
    data class Success(val photography: List<Photography>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(private val photographyRepository: PhotographyRepository): ViewModel(){

    // initial state
    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getPhotography() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = photographyRepository.getPhotography()
            mainUiState = MainUiState.Success(result.photography.orEmpty())
        } catch (e: IOException) {
            mainUiState = MainUiState.Error
        }
    }

    // block yg prtama dipanggil ktika ini dibuka
    init {
        getPhotography()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val photographyRepository = application.container.photograhyRepository
                MainViewModel(photographyRepository)
            }
        }
    }
}