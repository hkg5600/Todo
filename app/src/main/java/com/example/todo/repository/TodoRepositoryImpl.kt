package com.example.todo.repository

import com.example.todo.TodoDataSource
import com.example.todo.model.Todo

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

    override fun modifyTodo(todo: Todo) {
        return dataSource.modifyTodo(todo)
    }

    override fun findTodoByKeyword(keyword: String): List<Todo> {
        return dataSource.findTodoByKeyword(keyword)
    }

    override fun findDoneTodo(keyword: String?): List<Todo> {
        return dataSource.findDoneTodo(keyword)
    }

    override fun findProgressingTodo(keyword: String?): List<Todo> {
        return dataSource.findProgressingTodo(keyword)
    }

    override fun deleteTodo(todo: Todo) {
        dataSource.deleteTodo(todo)
    }

    override fun toggleTodo(todo: Todo) {
        dataSource.toggleTodo(todo)
    }
}