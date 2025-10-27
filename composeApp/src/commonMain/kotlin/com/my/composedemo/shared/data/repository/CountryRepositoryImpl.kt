package com.my.composedemo.shared.data.repository

import com.my.composedemo.shared.data.datasource.CountryDataSource
import com.my.composedemo.shared.domain.model.Country
import com.my.composedemo.shared.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow

/**
 * 国情報リポジトリの実装
 * ドメイン層のインターフェースを実装し、データソースを抽象化
 */
class CountryRepositoryImpl(
    private val countryDataSource: CountryDataSource
) : CountryRepository {
    
    override suspend fun getCountries(): Flow<List<Country>> {
        return countryDataSource.getCountries()
    }
    
    override suspend fun getCountryById(id: String): Country? {
        return countryDataSource.getCountryById(id)
    }
    
    override suspend fun toggleFavorite(countryId: String) {
        countryDataSource.toggleFavorite(countryId)
    }
    
    override suspend fun getFavoriteCountries(): Flow<List<Country>> {
        return countryDataSource.getFavoriteCountries()
    }
}
