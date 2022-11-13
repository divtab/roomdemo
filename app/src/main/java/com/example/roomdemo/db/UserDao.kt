package com.example.roomdemo.db

import androidx.room.*

interface UserDao {

    @Query("SELECT * FROM userinfo ORDER BY id DESC")
    fun getAllUserInfo(): List<UseEntity>

    @Insert
    fun insertUser(user: UseEntity?)

    @Insert
    fun deleteUser(user:UseEntity?)

    @Update
    fun updateUser(user: UseEntity?)
}