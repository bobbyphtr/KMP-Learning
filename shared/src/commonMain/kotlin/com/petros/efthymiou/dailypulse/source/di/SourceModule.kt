package com.petros.efthymiou.dailypulse.source.di

import com.petros.efthymiou.dailypulse.source.application.SourcesUseCase
import com.petros.efthymiou.dailypulse.source.data.SourcesLocalDataSource
import com.petros.efthymiou.dailypulse.source.data.SourcesLocalDataSourceImpl
import com.petros.efthymiou.dailypulse.source.data.SourcesRepository
import com.petros.efthymiou.dailypulse.source.data.SourcesService
import com.petros.efthymiou.dailypulse.source.presentation.SourcesViewModel
import org.koin.dsl.module

var sourceModule = module {
    single<SourcesViewModel> { SourcesViewModel(get()) }
    single<SourcesUseCase> { SourcesUseCase(get()) }
    single<SourcesRepository> { SourcesRepository(get(), get()) }
    single<SourcesLocalDataSource> { SourcesLocalDataSourceImpl(get()) }
    single<SourcesService> { SourcesService(get()) }
}