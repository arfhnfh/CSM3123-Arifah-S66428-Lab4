package com.example.roomdatabasedemoo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize ViewModel
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        // Initialize RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = UserAdapter(
            onUpdateClick = { user ->
                val updatedUser = User(user.id, "${user.name} (Updated)", user.age + 1)
                userViewModel.update(updatedUser)
            },
            onDeleteClick = { user ->
                userViewModel.delete(user)
            }
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe all users initially
        userViewModel.getAllUsers().observe(this) { users ->
            adapter.submitList(users)
        }

        // Add user functionality
        val addButton = findViewById<Button>(R.id.buttonAdd)
        val nameInput = findViewById<EditText>(R.id.editTextName)
        val ageInput = findViewById<EditText>(R.id.editTextAge)

        addButton.setOnClickListener {
            val name = nameInput.text.toString()
            val age = ageInput.text.toString().toIntOrNull()

            if (name.isNotBlank() && age != null) {
                val user = User(name = name, age = age)
                userViewModel.insert(user)
                nameInput.text.clear()
                ageInput.text.clear()
            }
        }

        // SearchView functionality
        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchUsers(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { searchUsers(it) }
                return true
            }
        })
    }

    private fun searchUsers(query: String) {
        userViewModel.searchUsers("%$query%").observe(this) { users ->
            adapter.submitList(users) // Update the adapter with filtered data
        }
    }
}
