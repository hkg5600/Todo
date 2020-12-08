package com.example.todo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.model.Todo
import com.example.todo.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(
    private val todoRepository: TodoRepository = DependencyInjection.todoRepository
) : ViewModel() {

    private val _state = MutableStateFlow<MainViewState>(MainViewState())
    val state: StateFlow<MainViewState>
        get() = _state

    init {
        getTodos()
    }

    fun getTodos() {
        viewModelScope.launch {
            val todos = todoRepository.getTodos()
            _state.value = MainViewState(todos.toList())
        }
    }

    fun toggleTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.toggleTodo(todo)
        }
    }

    fun addTodo(title: String) {
        viewModelScope.launch {
            val todo = Todo(
                id = Date().time.toString(),
                title = title,
                done = false,
                date = Date().time.toString()
            )
            todoRepository.addTodo(todo)
            getTodos()
        }
    }
}

data class MainViewState(
    val todos: List<Todo> = listOf(),
)