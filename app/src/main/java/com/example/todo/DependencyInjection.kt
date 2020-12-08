package com.example.todo

import android.content.Context
import com.example.todo.repository.TodoRepository
import com.example.todo.repository.TodoRepositoryImpl

object DependencyInjection {

    var context: Context? = null
        get() {
            return field ?: throw Exception("Context must be set!")
        }

    private val todoDataSource by lazy {
        TodoDataSource()
    }

    val todoRepository: TodoRepository by lazy {
        TodoRepositoryImpl(todoDataSource)
    }

    fun startApp(context: Context) {
        this.context = context
    }
}