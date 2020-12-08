package com.example.todo.repository

import androidx.lifecycle.LiveData
import com.example.todo.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun addTodo(todo: Todo)
    fun getTodoById(id: String): Todo
    fun getTodos(): ArrayList<Todo>
    fun modifyTodo(todo: Todo)
    fun findTodoByKeyword(keyword: String): List<Todo>
    fun findDoneTodo(keyword: String? = null): List<Todo>
    fun findProgressingTodo(keyword: String? = null): List<Todo>
    fun deleteTodo(todo: Todo)
    fun toggleTodo(todo: Todo)
}