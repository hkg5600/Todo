package com.example.todo

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import com.example.todo.ui.TodoTheme
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.todo.model.Todo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.example.todo.utils.parsColor

class MainActivity : AppCompatActivity() {

    @ExperimentalFocus
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            TodoTheme {
                Home()
            }
        }
    }
}

@ExperimentalFocus
@ExperimentalCoroutinesApi
@Composable
fun Home() {
    val viewModel: MainViewModel = viewModel(factory = viewModelProvider { MainViewModel() })
    val viewState by viewModel.state.collectAsState()

    var writeTodoVisibility by remember { mutableStateOf(false) }
    Scaffold(floatingActionButtonPosition = FabPosition.Center, floatingActionButton = {
        HomeFabButton(buttonState = writeTodoVisibility, onClick = {
            writeTodoVisibility = !writeTodoVisibility
        }, changeButtonState = { isFocused ->
            writeTodoVisibility = isFocused
        }, writeTodo = viewModel::addTodo)
    }, bodyContent = {
        HomeBody(viewState.todos, viewModel::toggleTodo) { writeTodoVisibility = true }
    })
}

@Composable
fun HomeBody(
    todos: List<Todo>,
    toggleTodoState: (todo: Todo) -> Unit,
    onItemClick: () -> Unit
) {
    ScrollableColumn(modifier = Modifier.background(Color.parsColor("#ededed")).fillMaxHeight()) {
        todos.map {
            TodoItem(todo = it, toggleTodoState = toggleTodoState, onItemClick = onItemClick)
        }
    }

}

@Composable
fun TodoItem(todo: Todo, toggleTodoState: (todo: Todo) -> Unit, onItemClick: () -> Unit) {
    Log.e("GHS Todo", todo.title)
    var done by remember { mutableStateOf(todo.done) }
    TodoItem(todo = todo, done = done, toggleTodoState = {
        done = it
        toggleTodoState(todo)
    }, onItemClick = onItemClick)
}

@Composable
fun TodoItem(
    todo: Todo,
    done: Boolean,
    toggleTodoState: (done: Boolean) -> Unit,
    onItemClick: () -> Unit
) {
    Card(
        backgroundColor = Color.parsColor("#ffffff"),
        shape = RoundedCornerShape(30),
        elevation = 0.dp,
        modifier = Modifier.padding(10.dp).fillMaxWidth().clickable(
            onClick = onItemClick
        ),
    ) {
        Log.e("GHS Title", todo.title)
        Row(
            horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(16.dp)
        ) {
            Checkbox(
                checked = done,
                onCheckedChange = toggleTodoState,
                modifier = Modifier.padding(3.dp)
            )
            Text(text = todo.title, modifier = Modifier.padding(3.dp))
        }
    }
}

@ExperimentalFocus
@Composable
fun HomeFabButton(
    passedTitle: String = "",
    buttonState: Boolean,
    changeButtonState: (isFocused: Boolean) -> Unit,
    onClick: () -> Unit,
    writeTodo: (String) -> Unit
) {

    var title by remember { mutableStateOf(passedTitle) }

    if (buttonState) {
        Card(
            backgroundColor = Color.parsColor("#ffffff"),
            shape = RoundedCornerShape(50),
            elevation = 3.dp
        ) {
            Row(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(value = title, onValueChange = {
                    title = it
                }, backgroundColor = Color.parsColor("#ffffff"),
                    imeAction = ImeAction.Done,
                    onImeActionPerformed = { action, softwareController ->
                        if (action == ImeAction.Done) {
                            softwareController?.hideSoftwareKeyboard()
                        }
                    }
                )
                Icon(
                    asset = vectorResource(id = R.drawable.ic_add),
                    modifier = Modifier.clickable(
                        onClick = {
                            if (title.isEmpty()) return@clickable
                            changeButtonState(false)
                            writeTodo(title)
                            title = ""
                        })
                )
            }
        }
    } else {
        ExtendedFloatingActionButton(
            backgroundColor = Color.parsColor("#ffffff"),
            shape = RoundedCornerShape(50),
            elevation = 3.dp,
            text = {
                Text(
                    text = "Write a new task",
                    color = Color.parsColor("#626262"),
                    modifier = Modifier.padding(
                        start = 15.dp,
                        end = 15.dp,
                        bottom = 3.dp,
                        top = 3.dp
                    )
                )
            },
            onClick = onClick,
        )
    }

}

@ExperimentalCoroutinesApi
@Preview
@Composable
fun DefaultPreview() {
    TodoTheme {

    }
}