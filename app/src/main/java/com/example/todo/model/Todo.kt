package com.example.todo.model

data class Todo(
    val title: String,
    val date: String,
    var done: Boolean
) {
    fun markAsDone() {
       this.done = true
    }

    fun markAsInProgress() {
        this.done = false
    }
}