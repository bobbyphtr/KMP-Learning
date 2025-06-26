package com.petros.efthymiou.dailypulse.source.data

import petros.efthymiou.dailypulse.db.DailyPulseDatabase

interface SourcesLocalDataSource {

    fun fetchSources() : List<SourcesRaw>

    fun insertSources(sources: List<SourcesRaw>)

    fun clearAllSources()

}

class SourcesLocalDataSourceImpl(
    private val database: DailyPulseDatabase
): SourcesLocalDataSource {
    override fun fetchSources(): List<SourcesRaw> {
        return database.dailyPulseDatabaseQueries.selectAllSource(::mapToSourcesRaw).executeAsList()
    }

    override fun insertSources(sources: List<SourcesRaw>) {
        database.dailyPulseDatabaseQueries.transaction {
            sources.forEach {
                insertSource(it)
            }
        }
    }

    override fun clearAllSources() {
        database.dailyPulseDatabaseQueries.removeAllSources()
    }

    private fun insertSource(source: SourcesRaw) {
        database.dailyPulseDatabaseQueries.insertSource(
            source.id,
            source.name,
            source.description,
            source.url,
            source.category,
            source.language,
            source.country
        )
    }

    private fun mapToSourcesRaw(
        id: String,
        name: String,
        description: String,
        url: String,
        category: String,
        language: String,
        country: String
    ): SourcesRaw =
        SourcesRaw(
            id, name, description, url, category, language, country
        )
}