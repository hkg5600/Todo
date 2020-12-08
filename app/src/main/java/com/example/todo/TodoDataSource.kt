package com.example.todo

import com.example.todo.data.FakeTodoDatabase
import com.example.todo.model.Todo

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

    fun findTodoByKeyword(keyword: String): List<Todo> {
        return FakeTodoDatabase.findTodoByKeyword(keyword)
    }

    fun findDoneTodo(keyword: String?) : List<Todo> {
        return FakeTodoDatabase.findDoneTodo(keyword)
    }

    fun findProgressingTodo(keyword: String?) : List<Todo> {
        return FakeTodoDatabase.findProgressingTodo(keyword)
    }

    fun deleteTodo(todo: Todo) {
        FakeTodoDatabase.deleteTodo(todo)
    }

    fun toggleTodo(todo: Todo) {
        FakeTodoDatabase.toggleTodo(todo)
    }
}