package com.petros.efthymiou.dailypulse.source.data

class SourcesRepository(
    val sourcesLocalDataSource: SourcesLocalDataSource,
    val sourcesService: SourcesService
) {
    suspend fun getAllSources() : List<SourcesRaw> {
        val sourcesDb = sourcesLocalDataSource.fetchSources()
        if(sourcesDb.isEmpty()) {
            val fetchedSourcesRaw = sourcesService.fetchSources()
            sourcesLocalDataSource.clearAllSources()
            sourcesLocalDataSource.insertSources(fetchedSourcesRaw)
            return fetchedSourcesRaw
        }
        return sourcesDb
    }
}