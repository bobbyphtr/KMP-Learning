package com.petros.efthymiou.dailypulse.di

import com.petros.efthymiou.dailypulse.articles.di.articlesModule
import com.petros.efthymiou.dailypulse.source.di.sourceModule

val sharedKoinModules = listOf(
    networkModule,
    articlesModule,
    sourceModule
)