package com.example.todo

import com.example.todo.model.Todo
import com.example.todo.repository.TodoRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TodoRepositoryTest {

    private lateinit var todoRepository: TodoRepository

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        todoRepository = DependencyInjection.todoRepository
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun clearDispatcher() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun get_todos() = runBlockingTest {
        val todos = todoRepository.getTodos()
        assert(todos.isNotEmpty())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun delete_first_todo() = runBlockingTest {
        val todos = todoRepository.getTodos()
        assert(todos.isNotEmpty())

        todoRepository.deleteTodo(todos.first())

        val deletedTodos = todoRepository.getTodos()
        assert(todos.size != deletedTodos.size)
    }

}