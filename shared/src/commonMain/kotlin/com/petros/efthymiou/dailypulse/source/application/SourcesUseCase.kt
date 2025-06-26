package com.petros.efthymiou.dailypulse.source.application

import com.petros.efthymiou.dailypulse.source.data.SourcesRaw
import com.petros.efthymiou.dailypulse.source.data.SourcesRepository

class SourcesUseCase(
    val repository: SourcesRepository
) {
    suspend fun fetchSources(): List<Source> = mapToListOfSource(repository.getAllSources())

    private fun mapToListOfSource(rawSource: List<SourcesRaw>): List<Source> =
        rawSource.map {
            Source(
                sourceName = it.name,
                sourceCountry = it.country,
                sourceDescription =  it.description,
                sourceLanguage = it.language
            )
        }

}

