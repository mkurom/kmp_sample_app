package com.my.composedemo.data.datasource

import com.my.composedemo.domain.model.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * 国情報のデータソース
 * 実際のデータアクセス（API、ローカルDB等）を抽象化
 */
interface CountryDataSource {
    suspend fun getCountries(): Flow<List<Country>>
    suspend fun getCountryById(id: String): Country?
    suspend fun toggleFavorite(countryId: String)
    suspend fun getFavoriteCountries(): Flow<List<Country>>
}

/**
 * メモリベースの国情報データソース実装
 * 実際のプロジェクトでは、RoomやAPIクライアントを使用
 */
class InMemoryCountryDataSource : CountryDataSource {
    private val _countries = MutableStateFlow(Country.createDefaultCountries())
    private val _favorites = MutableStateFlow<Set<String>>(emptySet())
    
    override suspend fun getCountries(): Flow<List<Country>> {
        return _countries.asStateFlow()
    }
    
    override suspend fun getCountryById(id: String): Country? {
        return _countries.value.find { it.id == id }
    }
    
    override suspend fun toggleFavorite(countryId: String) {
        val currentFavorites = _favorites.value.toMutableSet()
        if (currentFavorites.contains(countryId)) {
            currentFavorites.remove(countryId)
        } else {
            currentFavorites.add(countryId)
        }
        _favorites.value = currentFavorites
        
        // 国情報のお気に入り状態を更新
        val updatedCountries = _countries.value.map { country ->
            if (country.id == countryId) {
                country.copy(isFavorite = !country.isFavorite)
            } else {
                country
            }
        }
        _countries.value = updatedCountries
    }
    
    override suspend fun getFavoriteCountries(): Flow<List<Country>> {
        return _countries.asStateFlow().let { flow ->
            kotlinx.coroutines.flow.combine(flow, _favorites.asStateFlow()) { countries, favorites ->
                countries.filter { favorites.contains(it.id) }
            }
        }
    }
}
