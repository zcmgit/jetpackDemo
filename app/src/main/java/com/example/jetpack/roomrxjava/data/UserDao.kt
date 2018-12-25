package com.example.jetpack.roomrxjava

import androidx.room.*
import com.example.jetpack.roomrxjava.entity.UserEntity
import io.reactivex.Flowable


@Dao
interface UserDao{

    @Insert
    fun insertList(userEntity: ArrayList<UserEntity>)

    @Insert
    fun insert(userEntity: UserEntity)

    @Query("SELECT * FROM user_info")
    fun searchAllInfo(): Flowable<List<UserEntity>>

    @Delete
    fun delete(userEntity: UserEntity)

    @Query("DELETE FROM user_info")
    fun deleteAll()

    @Update
    fun update(userEntity: UserEntity)
}