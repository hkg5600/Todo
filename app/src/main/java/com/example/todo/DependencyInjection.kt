package com.example.todo

import android.content.Context
import com.example.todo.repository.TodoRepository
import com.example.todo.repository.TodoRepositoryImpl

object DependencyInjection {

    var context: Context? = null
        get() {
            return field ?: throw Exception("There is no context set")
        }

    private val todoDataSource = TodoDataSource()

    val todoRepository: TodoRepository by lazy {
        TodoRepositoryImpl(todoDataSource)
    }

    fun start(context: Context) {
        this.context = context
    }
}