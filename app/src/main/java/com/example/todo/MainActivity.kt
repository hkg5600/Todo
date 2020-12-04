package com.example.todo

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import com.example.todo.ui.TodoTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.DragObserver
import androidx.compose.ui.gesture.dragGestureFilter
import androidx.compose.ui.gesture.longPressDragGestureFilter
import androidx.compose.ui.gesture.longPressGestureFilter
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.IconCompat
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainActivity : AppCompatActivity() {

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = Color.Cyan, modifier = Modifier.fillMaxSize()) {
                    ScrollableColumn() {
                        for (i in 0..100) {
                            Greeting(index = i)
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalCoroutinesApi
@Composable
fun Greeting(index: Int) {
    var longClicking by remember { mutableStateOf(false) }
    var x by remember { mutableStateOf(0f) }
    var y by remember { mutableStateOf(0f) }

    val offsetX = with(DensityAmbient.current) {
        x.toDp()
    }
    val offsetY = with(DensityAmbient.current) {
        y.toDp()
    }

    val viewModel: MainViewModel = viewModel(factory = viewModelProvider { MainViewModel() })

    val viewState by viewModel.state.collectAsState()
    ClickableText(onClick = {} ,text = AnnotatedString(index.toString()),
        modifier = Modifier.padding(50.dp).background(Color.DarkGray).offset(offsetX, offsetY).size(156.dp, 56.dp).longPressGestureFilter {
            longClicking = true
        }.dragGestureFilter(
            // The core Drag and Drop feature
            object : DragObserver {
                override fun onDrag(dragDistance: Offset): Offset {
                    if (longClicking) {
                        x += dragDistance.x
                        y += dragDistance.y
                    }
                    return dragDistance
                }

                override fun onStop(velocity: Offset) {
                    super.onStop(velocity)
                    longClicking = false
                }
            }
        ))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TodoTheme {
    }
}