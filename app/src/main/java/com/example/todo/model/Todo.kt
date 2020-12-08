package com.example.todo.model

data class Todo(
    val id : String,
    var title: String,
    val date: String,
    var done: Boolean
) {
    private fun markAsDone() {
       this.done = true
    }

    private fun markAsInProgress() {
        this.done = false
    }

    fun toggleTodo() {
        if (this.done)
            markAsInProgress()
        else
            markAsDone()
    }
}