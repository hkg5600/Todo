package com.example.todo.data

import com.example.todo.model.Todo
import java.util.*
import kotlin.collections.ArrayList

object FakeTodoDatabase {
    var times = 0
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
//        val todo = Todo(
//            id = "id1",
//            title = "Changed Todo ${++times}",
//            date = "test",
//            done = true
//        )
//        modifyTodo(todo)
        return todos
    }

    fun modifyTodo(todo: Todo) {
        val changeIndex =
            todos.indexOf(todos.find { it.id == todo.id }) // Get todo's index will change
        todos[changeIndex] = todo
    }

    fun findTodoByKeyword(keyword: String): List<Todo> {
        return todos.filter { it.title.contains(keyword) }
    }

    fun findDoneTodo(keyword: String?): List<Todo> {
        val doneTodo = todos.filter { it.done }
        return if (keyword != null) doneTodo.filter { it.title.contains(keyword) } else doneTodo
    }

    fun findProgressingTodo(keyword: String?): List<Todo> {
        val progressingTodo = todos.filter { !it.done }
        return if (keyword != null) progressingTodo.filter { it.title.contains(keyword) } else progressingTodo
    }

    fun deleteTodo(todo: Todo) {
        todos.remove(todo)
    }

    fun toggleTodo(todo: Todo) {
        val index = todos.indexOf(todo)
        todos[index].toggleTodo()
    }

}