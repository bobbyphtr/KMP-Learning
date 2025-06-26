package com.petros.efthymiou.dailypulse.articles.data

import petros.efthymiou.dailypulse.db.DailyPulseDatabase

class ArticlesDataSource(private val database: DailyPulseDatabase) {

    fun getAllArticles() : List<ArticleRaw> =
        database.dailyPulseDatabaseQueries.selectAllArticles(::mapToArticleRaw).executeAsList()

    fun insertArticles(articles: List<ArticleRaw>) {
        // all transaction must be done together and all should be successful, if not will be reverted
        database.dailyPulseDatabaseQueries.transaction {
            articles.forEach { articleRaw -> insertArticle(articleRaw) }
        }
    }

    fun clearArticles() = database.dailyPulseDatabaseQueries.removeAllArticles()

    private fun insertArticle(articleRaw: ArticleRaw) {
        database.dailyPulseDatabaseQueries.insertArticle(
            articleRaw.title, articleRaw.desc, articleRaw.date, articleRaw.imageUrl
        )
    }

    private fun mapToArticleRaw(
        title: String,
        desc: String?,
        date: String,
        imageUrl: String?
    ) : ArticleRaw = ArticleRaw(
        title, desc, date, imageUrl
    )
}