package com.example.roomdatabasedemoo

import android.app.Application
import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun update(user: User) {
        userDao.update(user)  // Implement update method
    }

    suspend fun delete(user: User) {
        userDao.delete(user)  // Implement delete method
    }

    fun getAllUsers(): LiveData<List<User>> {
        return userDao.getAllUsers()
    }

    fun searchUsers(name: String): LiveData<List<User>> {
        return userDao.searchUsersByName(name)
    }
}
