package com.example.todo.repository

import androidx.lifecycle.LiveData
import com.example.todo.TodoDataSource
import com.example.todo.model.Todo
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(private val dataSource: TodoDataSource) : TodoRepository {
    override fun addTodo(todo: Todo) {
        dataSource.addTodo(todo)
    }

    override fun getTodoById(id: String): Todo {
        return dataSource.getTodoById(id)
    }

    override fun getTodos(): ArrayList<Todo> {
        return dataSource.getTodos()
    }

    override fun deleteTodo(todo: Todo) {
        dataSource.deleteTodo(todo)
    }

    override fun markDone(todo: Todo) {
        dataSource.markDone(todo)
    }

    override fun markInProgress(todo: Todo) {
        dataSource.markInProgress(todo)
    }
}