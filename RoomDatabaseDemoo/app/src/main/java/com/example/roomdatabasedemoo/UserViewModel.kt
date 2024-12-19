package com.example.roomdatabasedemoo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    fun insert(user: User) {
        viewModelScope.launch {
            repository.insert(user)
        }
    }

    fun update(user: User) {
        viewModelScope.launch {
            repository.update(user) // Update user in the repository
        }
    }

    fun delete(user: User) {
        viewModelScope.launch {
            repository.delete(user) // Delete user from the repository
        }
    }

    fun getAllUsers(): LiveData<List<User>> {
        return repository.getAllUsers()
    }

    fun searchUsers(name: String): LiveData<List<User>> {
        return repository.searchUsers(name)
    }
}



