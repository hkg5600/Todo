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
            "id1",
            "Test1",
            "2020.12.01",
            false
        ),
        Todo(
            "id2",
            "Test2",
            "2020.12.01",
            true
        ),
        Todo(
            "id3",
            "Test3",
            "2020.12.02",
            false
        )
    )

    fun addTodo(todo: Todo) {
        todos.add(todo)
    }

    fun getTodoById(id: String): Todo {
        return todos.single { it.id == id }
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