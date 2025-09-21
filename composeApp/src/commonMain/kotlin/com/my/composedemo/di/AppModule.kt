package com.my.composedemo.di

import com.my.composedemo.data.datasource.InMemoryCountryDataSource
import com.my.composedemo.data.repository.CountryRepositoryImpl
import com.my.composedemo.domain.repository.CountryRepository
import com.my.composedemo.presentation.viewmodel.AppViewModel
import com.my.composedemo.presentation.viewmodel.HomeViewModel
import org.koin.dsl.module

/**
 * アプリケーションの依存性注入モジュール
 * Koinを使用してDIを設定
 */
val appModule = module {
    
    // データソース
    single<com.my.composedemo.data.datasource.CountryDataSource> { 
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
