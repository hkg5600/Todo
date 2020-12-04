package com.example.todo

import com.example.todo.model.Todo
import com.example.todo.repository.TodoRepository
import kotlinx.coroutines.*
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

        todoRepository.deleteTodo(todos.first())

        val deletedTodos = todoRepository.getTodos()
        assert(todos.size != deletedTodos.size)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun modify_todo() = runBlockingTest {
        val oldTodos = todoRepository.getTodos()
        val oldTodo = oldTodos[0]

        val newTodo = Todo(
            id = oldTodo.id,
            title = "New Title",
            date = "New Date",
            done = true
        )

        todoRepository.modifyTodo(newTodo)

        val newTodos = todoRepository.getTodos()

        assert(newTodos[0].title == "New Title")
    }

    @ExperimentalCoroutinesApi
    @Test
    fun find_todo_by_keyword() = runBlockingTest {
        val keyword = "Test1"

        val todo = todoRepository.findTodoByKeyword(keyword)

        assert(todo[0].title == "Test1")
    }

    @ExperimentalCoroutinesApi
    @Test
    fun find_done_todo() = runBlockingTest {
        val doneTodos = todoRepository.findDoneTodo()

        assert(doneTodos.none { !it.done })
    }

    @ExperimentalCoroutinesApi
    @Test
    fun find_progressing_todo() = runBlockingTest {
        val progressingTodos = todoRepository.findProgressingTodo()

        assert(progressingTodos.none { it.done })
    }

    @ExperimentalCoroutinesApi
    @Test
    fun find_done_todo_by_keyword() = runBlockingTest {
        val keyword = "Test1"

        val doneTodos = todoRepository.findDoneTodo(keyword)

        assert(doneTodos.isEmpty())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun find_progressing_todo_by_keyword() = runBlockingTest {
        val keyword = "Test1"

        val doneTodos = todoRepository.findProgressingTodo(keyword)

        assert(doneTodos.isNotEmpty())
    }
}
