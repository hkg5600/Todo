package com.example.todo.repository

import androidx.lifecycle.LiveData
import com.example.todo.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun addTodo(todo: Todo)
    fun getTodos() : ArrayList<Todo>
    fun deleteTodo(todo: Todo)
    fun markDone(todo: Todo)
    fun markInProgress(todo: Todo)
}