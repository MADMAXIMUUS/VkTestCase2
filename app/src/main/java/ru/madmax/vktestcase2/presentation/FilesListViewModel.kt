package ru.madmax.vktestcase2.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.madmax.vktestcase2.domain.model.FileModel
import ru.madmax.vktestcase2.domain.repository.FileRepository
import javax.inject.Inject

@HiltViewModel
class FilesListViewModel @Inject constructor(
    private val repository: FileRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(FileListScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val files = repository.getAllFiles(_uiState.value.sortProperty).map(FileModel::toFileItemState)
            _uiState.update { currentState->
                currentState.copy(
                    files = files
                )
            }
        }
    }

}