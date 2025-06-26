package com.petros.efthymiou.dailypulse.source.presentation

import com.petros.efthymiou.dailypulse.source.application.Source

data class SourcesState(
    val isLoading: Boolean = false,
    val sources: List<Source> = listOf(),
    val error: String? = null
)