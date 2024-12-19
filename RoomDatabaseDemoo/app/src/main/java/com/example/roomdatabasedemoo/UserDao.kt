package com.example.roomdatabasedemoo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User) // Update user query

    @Delete
    suspend fun delete(user: User) // Delete user query

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getAllUsers(): LiveData<List<User>>

    @Query ("SELECT * FROM user_table WHERE name LIKE :name")
    fun searchUsersByName(name: String): LiveData<List<User>>

}


