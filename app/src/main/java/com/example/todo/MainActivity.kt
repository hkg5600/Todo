package com.example.todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import com.example.todo.ui.TodoTheme
import androidx.compose.runtime.getValue
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = Color.Red) {
                    Greeting("Android")
                }
            }
        }
    }
}

@ExperimentalCoroutinesApi
@Composable
fun Greeting(name: String) {

    val viewModel: MainViewModel = viewModel(factory = viewModelProvider { MainViewModel() })

    val viewState by viewModel.state.collectAsState()

    Column {
        Text(text = viewState.todos.last().title)
        Button(onClick = {
            viewModel.test()
        }) {
            Text(text = "Click me")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TodoTheme {
        Greeting("Android")
    }
}