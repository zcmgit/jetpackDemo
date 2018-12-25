package com.example.jetpack.room.data

import androidx.room.*
import com.example.jetpack.room.entity.UserEntity

@Dao
interface UserDao{

    @Insert
    fun insertList(userEntity: ArrayList<UserEntity>)

    @Insert
    fun insert(userEntity: UserEntity)

    @Query("SELECT * FROM user_info")
    fun searchAllInfo(): List<UserEntity>

    @Delete
    fun delete(userEntity: UserEntity)

    @Query("DELETE FROM user_info")
    fun deleteAll()

    @Update
    fun update(userEntity: UserEntity)
}