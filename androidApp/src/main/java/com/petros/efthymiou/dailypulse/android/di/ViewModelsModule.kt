package com.petros.efthymiou.dailypulse.android.di

import com.petros.efthymiou.dailypulse.articles.presentation.ArticlesViewModel
import com.petros.efthymiou.dailypulse.source.presentation.SourcesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        ArticlesViewModel(get())
        SourcesViewModel(get())
    }
}