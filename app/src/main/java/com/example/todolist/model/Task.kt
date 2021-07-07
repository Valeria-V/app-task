package com.example.todolist.model

import java.util.*

data class Task(
    val title: String,
    val hour: String,
    val date: String,
    val id: Int = 0
)
