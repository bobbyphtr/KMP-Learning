package com.petros.efthymiou.dailypulse.source.presentation

import com.petros.efthymiou.dailypulse.BaseViewModel
import com.petros.efthymiou.dailypulse.source.application.SourcesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SourcesViewModel(
    val useCase: SourcesUseCase
): BaseViewModel() {

    val sourceState: StateFlow<SourcesState> get() = _sourcesState

    private val _sourcesState: MutableStateFlow<SourcesState> = MutableStateFlow(SourcesState(isLoading = true))

    init {
        getSources()
    }

    fun getSources(forceFetch: Boolean = false) {
        scope.launch {
            _sourcesState.emit(SourcesState(isLoading = true, sources = _sourcesState.value.sources))

            val fetchedSources = useCase.fetchSources()

            _sourcesState.emit(SourcesState(sources = fetchedSources))
        }
    }

}

