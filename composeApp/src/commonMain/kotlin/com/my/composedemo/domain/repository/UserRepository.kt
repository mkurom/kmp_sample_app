package com.my.composedemo.domain.repository

import com.my.composedemo.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * ユーザー情報を管理するリポジトリインターフェース
 */
interface UserRepository {
    /**
     * 現在のユーザー情報を取得
     */
    suspend fun getCurrentUser(): Flow<User?>
    
    /**
     * ユーザー情報を更新
     */
    suspend fun updateUser(user: User)
    
    /**
     * ユーザーの設定を更新
     */
    suspend fun updateUserPreferences(preferences: com.my.composedemo.domain.model.UserPreferences)
}
