package com.my.composedemo.shared.domain.repository

import com.my.composedemo.shared.domain.model.Country
import kotlinx.coroutines.flow.Flow

/**
 * 国情報を管理するリポジトリインターフェース
 * ドメイン層から外部のデータアクセスを抽象化
 */
interface CountryRepository {
    /**
     * すべての国情報を取得
     */
    suspend fun getCountries(): Flow<List<Country>>
    
    /**
     * 特定の国の情報を取得
     */
    suspend fun getCountryById(id: String): Country?
    
    /**
     * 国をお気に入りに追加/削除
     */
    suspend fun toggleFavorite(countryId: String)
    
    /**
     * お気に入りの国一覧を取得
     */
    suspend fun getFavoriteCountries(): Flow<List<Country>>
}

