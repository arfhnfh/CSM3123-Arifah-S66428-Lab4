package com.example.roomdatabasedemoo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(
    private val onUpdateClick: (User) -> Unit,
    private val onDeleteClick: (User) -> Unit
) : ListAdapter<User, UserAdapter.UserViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false) // Use custom layout
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textName: TextView = itemView.findViewById(R.id.text_name)
        private val textAge: TextView = itemView.findViewById(R.id.text_age)
        private val buttonUpdate: Button = itemView.findViewById(R.id.button_update)
        private val buttonDelete: Button = itemView.findViewById(R.id.button_delete)

        fun bind(user: User) {
            textName.text = user.name
            textAge.text = "Age: ${user.age}"

            // Handle Update button click
            buttonUpdate.setOnClickListener {
                onUpdateClick(user)
            }

            // Handle Delete button click
            buttonDelete.setOnClickListener {
                onDeleteClick(user)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}
