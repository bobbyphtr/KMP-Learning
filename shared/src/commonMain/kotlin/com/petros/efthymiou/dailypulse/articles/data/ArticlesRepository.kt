package com.petros.efthymiou.dailypulse.articles.data

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private val service: ArticlesService
) {

    suspend fun getArticles(forceFetch: Boolean): List<ArticleRaw> {
        val articlesDb = dataSource.getAllArticles()

        println("Got ${articlesDb.size} from Database!")

        if(articlesDb.isEmpty() || forceFetch) {
            dataSource.clearArticles()
            val fetchedArticles = service.fetchArticles()
            dataSource.insertArticles(fetchedArticles)
            println("Fetched ${fetchedArticles.size} from Remote! and Saving to DB!")
            return fetchedArticles
        }
        return articlesDb
    }

}