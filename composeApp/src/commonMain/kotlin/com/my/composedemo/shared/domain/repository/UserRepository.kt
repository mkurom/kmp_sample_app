package com.my.composedemo.shared.domain.repository

import com.my.composedemo.shared.domain.model.User
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
    suspend fun updateUserPreferences(preferences: com.my.composedemo.shared.domain.model.UserPreferences)
}

