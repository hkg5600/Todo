package com.example.todo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.todo.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf

object FakeTodoDatabase {

    private var todos = arrayListOf<Todo>(
        Todo(
            "Test1",
            "2020.12.01",
            false
        ),
        Todo(
            "Test2",
            "2020.12.01",
            true
        ),
        Todo(
            "Test3",
            "2020.12.02",
            false
        )
    )

    fun addTodo(todo: Todo) {
        todos.add(todo)
    }

    fun getTodos(): ArrayList<Todo> {
        return todos
    }

    fun deleteTodo(todo: Todo) {
        todos.remove(todo)
    }

    fun markDone(todo: Todo) {
        val index = todos.indexOf(todo)
        todos[index].markAsDone()
    }

    fun markInProgress(todo: Todo) {
        val index = todos.indexOf(todo)
        todos[index].markAsInProgress()
    }
}