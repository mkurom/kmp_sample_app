package com.my.composedemo.shared.di

import com.my.composedemo.shared.data.datasource.InMemoryCountryDataSource
import com.my.composedemo.shared.data.repository.CountryRepositoryImpl
import com.my.composedemo.shared.domain.repository.CountryRepository
import com.my.composedemo.shared.presentation.viewmodel.AppViewModel
import com.my.composedemo.shared.presentation.viewmodel.HomeViewModel
import org.koin.dsl.module

/**
 * アプリケーションの依存性注入モジュール
 * Koinを使用してDIを設定
 */
val appModule = module {
    
    // データソース
    single<com.my.composedemo.shared.data.datasource.CountryDataSource> { 
        InMemoryCountryDataSource() 
    }
    
    // リポジトリ
    single<CountryRepository> { 
        CountryRepositoryImpl(get()) 
    }
    
    // ViewModel（ユースケースの役割も兼ねる）
    single { AppViewModel() }
    single { HomeViewModel(get(), get()) }
}
