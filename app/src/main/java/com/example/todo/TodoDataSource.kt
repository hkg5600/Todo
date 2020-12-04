package com.example.todo

import androidx.lifecycle.LiveData
import com.example.todo.data.FakeTodoDatabase
import com.example.todo.model.Todo
import kotlinx.coroutines.flow.Flow

class TodoDataSource {
    fun addTodo(todo: Todo) {
        FakeTodoDatabase.addTodo(todo)
    }

    fun getTodoById(id: String): Todo {
        return FakeTodoDatabase.getTodoById(id)
    }

    fun getTodos(): ArrayList<Todo> {
        return FakeTodoDatabase.getTodos()
    }

    fun modifyTodo(todo: Todo) {
        FakeTodoDatabase.modifyTodo(todo)
    }

    fun deleteTodo(todo: Todo) {
        FakeTodoDatabase.deleteTodo(todo)
    }

    fun markDone(todo: Todo) {
        FakeTodoDatabase.markDone(todo)
    }

    fun markInProgress(todo: Todo) {
        FakeTodoDatabase.markInProgress(todo)
    }
}