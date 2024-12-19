package com.example.roomdatabasedemoo

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithTask(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val tasks: List<Task>
)